package mediator;

import player.Player;
import role.*;

import java.util.*;
import java.util.function.BiFunction;


// pour des parties de type "III distribution des cartes pour parties simplifiées" : https://www.regledujeu.fr/loup-garou-regle/

public class SimpleGameMediator implements Mediator{
    private final MediatorState mediatorState;
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private Seer seer;
    private final List<Role> roles;         // pour pouvoir facilement accéder à tous les rôles d'un seul coup
    private final List<Role> livingRoles;
    private boolean gameOver = false;

    private int turn = 0;

    // les rôles essentiels à attribuer dans une partie de 8 joueurs
    private final static List<BiFunction<Player, MediatorState, Role>> primaryRoles = List.of(
            WereWolf::new, WereWolf::new, Seer::new, Villager::new,
            Villager::new, Villager::new, Villager::new, Villager::new
    );

    // rôles à attribuer en plus si assez de joueurs
    private final static List<BiFunction<Player, MediatorState, Role>> otherRoles = List.of(
            Villager::new, Villager::new, Villager::new, WereWolf::new  // ...
    );


    public SimpleGameMediator(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
        this.villagers = new ArrayList<>();
        this.wereWolves = new ArrayList<>();

        this.roles = new ArrayList<>();
        this.livingRoles = new LinkedList<>();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Vérifier les conditions de victoire de la partie. La partie s'arrête soit si les loups garous sont tous morts,
     * soit si il n'y a plus assez de villageois pour les battre lors d'un vote de vilalge
     */
    private void computeWinConditions(){
        boolean wolvesWon = true;
        boolean villageWon = true;

        boolean atLeastOneLivingWolf = false;

        for (Role role : livingRoles){
            if (!(role instanceof WereWolf)) {
                wolvesWon = false;
                break;
            }
        }
        for (Role wolf : wereWolves){
            if (wolf.isAlive()){
                atLeastOneLivingWolf = true;
                villageWon = false;
                break;
            }
        }

        if (livingRoles.size() == 2 && atLeastOneLivingWolf){   // pas suffisant commen condition dans un potentiel 2 vs 2 ?
            wolvesWon = true;       // car dans n'importe quel vote de village, le loup n'acceptera jamais de voter pour lui-même
        }

        if (wolvesWon) {
            System.out.println("Les loups garous ont gagné.");
        } else if (villageWon) {
            System.out.println("Le village a gagné !");
        }

        if (wolvesWon || villageWon) gameOver = true;
    }

    public void broadcastMessage(String message) {
        for (Role role : roles) {
            role.sendGameInformation(message);
        }
    }

    // pour afficher brièvement l'état de la partie (qui est en vie, qui pas)
    public void displayCurrentGameState(){
        System.out.println("Current game state: ");
        for (Role role : roles) {
            System.out.println(role + " (" + (role.isAlive() ? "alive" : "dead")   + ")");
        }
    }

    private Role getRoleOfPlayer(Player player){
        for (Role role : roles) {
            if (role.getPlayer().equals(player)) {
                return role;
            }
        }
        System.out.println("No role found for player " + player.getName());
        throw new RuntimeException("No role found for player " + player.getName()); // comme c'est pas censé arriver, je sais pas quoi faire de ça
    }


    /**
     * Cette fonction existe pour créer un vote, par exemple quand les loups garous ou le village doivent voter pour l'élimination d'un villageois,
     * @param voters le groupe de votants
     * @param chooseAmong parmi quel sous-ensemble on propose aux votants de voter
     * @return le joueur sélectionné
     */
    private Player killVote(List<? extends Role> voters, List<Role> chooseAmong) {
        // vérifier que le chooseAmong ne contienne pas de joueurs morts ?
        Player chosenPlayer;
        List<Player> candidates = new ArrayList<>();
        List<Player> toRemove = new ArrayList<>();
        Map<Player, Integer> map = new HashMap<>(); // map qui sert à récolter les votes pour un joueur lors d'un "tour de vote"
        chooseAmong.forEach(role -> {candidates.add(role.getPlayer());});

        int voteTurn = 0;
        while (candidates.size() != 1 && voteTurn < 10) {
            ++voteTurn;
            System.out.println("Vote turn " + voteTurn);
            map.clear();
            // demander aux votants de voter
            for (Role voter : voters) {
                Player choice = voter.choosePlayer(candidates);
                map.merge(choice, 1, Integer::sum);
            }

            // comptabiliser le nombre de voix. Si il y a une égalité, recommencer
            candidates.clear();
            Integer mostChosenAmount = 0;
            Player mostChosenPlayer = null;
            for (Map.Entry<Player, Integer> entry : map.entrySet()) {
                if (mostChosenPlayer == null) {
                    mostChosenPlayer = entry.getKey();
                    mostChosenAmount = entry.getValue();
                    candidates.add(mostChosenPlayer);
                    continue;
                }
                if (entry.getValue() > mostChosenAmount) {
                    mostChosenAmount = entry.getValue();
                    mostChosenPlayer = entry.getKey();
                    candidates.clear();
                    candidates.add(mostChosenPlayer);
                } else if (entry.getValue().equals(mostChosenAmount)) {
                    candidates.add(entry.getKey());
                }
            }

        }

        chosenPlayer = candidates.getFirst();

        System.out.println("Selected : " + chosenPlayer);

        return chosenPlayer;
    }

    @Override
    public void playTurn() {
        if (isGameOver()){
            System.out.println("Can't play turn because game over");
        }
        ++turn;
        System.out.println("Tour " + turn);
        displayCurrentGameState();

        System.out.println("Le village s'endort");
        // appeller la voyante, lui faire choisir un joueur, et lui donner son rôle
        System.out.println("La voyante se réveille, et désigne un joueur dont elle veut sonder la personnalité");
        Player chosenPlayer = seer.choosePlayer(mediatorState.getPlayers());

        for (Role role : roles) {
            if (role.getPlayer() == chosenPlayer) {
                seer.sendGameInformation("The chosen player has role : " + role.getRoleName());
                break;
            }
        }

        // demander aux loups-garou de voter pour éliminer un joueur
        System.out.println("les Loups-Garous se réveillent, se reconnaissent et désignent une nouvelle victime !!!");

        ArrayList livingWereWolves = new ArrayList();
        wereWolves.forEach(role -> {if (role.isAlive()) livingWereWolves.add(role);});
        Role killedRole = getRoleOfPlayer(killVote(livingWereWolves, livingRoles));
        killedRole.kill();
        livingRoles.remove(killedRole);
        System.out.println("Le village se réveille, et découvrent que pendant la nuit, les loups-garous ont tué : " + killedRole);

        // avant de réveiller le village, vérifier que la partie n'est pas terminée, pour éviter un vote impossible (un loup garou et un villageois)
        computeWinConditions();
        if (gameOver) return;

        // réveiller tout le monde pour qu'ils votent pour l'élimination d'un joueur
        System.out.println("Le village va voter l'élimination d'un joueur.");
        Role killedByVillage = getRoleOfPlayer(killVote(livingRoles, livingRoles));
        killedByVillage.kill();
        livingRoles.remove(killedRole);
        System.out.println("Le village a voté l'élimination de " + killedRole);

        computeWinConditions();
        if (gameOver) return;

        System.out.println("Fin du tour" + "\n" + "\n"+ "\n");
    }

    @Override   // retourne les joueurs du mediatorState associé
    public List<Player> getPlayers() {
        return mediatorState.getPlayers();
    }

    @Override
    public void assignRoles() {
        List<Player> players = getPlayers();
        Collections.shuffle(players);

        for (int i = 0; i < players.size(); ++i) {
            BiFunction<Player, MediatorState, Role> constructor = i >= primaryRoles.size() ?
                    otherRoles.get(i % otherRoles.size()) :
                    primaryRoles.get(i);
            Role role = constructor.apply(players.get(i), mediatorState);

            switch (role) {
                case WereWolf w -> wereWolves.add(w);
                case Villager v -> villagers.add(v);
                case Seer s -> seer = s;
                default -> throw new IllegalStateException("Unexpected role: " + role);
            }
        }

        roles.addAll(villagers);
        roles.addAll(wereWolves);
        roles.add(seer);
    }

    @Override
    public int getMinPlayers() {
        return 8;
    }

    @Override
    public int getMaxPlayers() {
        return 18;
    }

    @Override
    public boolean start() {
        if (!(getMinPlayers() <= getPlayers().size() && getPlayers().size() <= getMaxPlayers())) {
            return false;
        }
        assignRoles();
        System.out.println("Assigned roles !");
        livingRoles.addAll(roles);
        return true;
    }
}