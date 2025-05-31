package mediator;

import player.Player;
import role.*;

import javax.print.DocFlavor;
import java.util.*;
import java.util.function.BiFunction;


// pour des parties de type "III distribution des cartes pour parties simplifiées" : https://www.regledujeu.fr/loup-garou-regle/

public class SimpleGameMediator implements Mediator{
    private final MediatorState mediatorState;
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private Seer seer = null;
    private final List<Role> roles;     // pour pouvoir facilement accéder à tous les rôles d'un seul coup
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
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public void broadcastMessage(String message) {
        for (Role role : roles) {
            role.sendGameInformation(message);
        }
    }

    public void displayCurrentGameState(){
        System.out.println("Current game state: ");
        for (Role role : roles) {
            System.out.println(role + " (" + (role.isAlive() ? "alive" : "dead")   + ")");
        }
    }

    private void createVote(List<Role> voters, List<Role> chooseAmong) {
        List<Player> p = new ArrayList<>();
        Map<Player, Integer> map = new HashMap<>();
        chooseAmong.forEach(role -> {p.add(role.getPlayer());});
        boolean choiceMade = false;
        while (!choiceMade) {
            for (Role role : voters) {
                Player choice = role.choosePlayer(p);
                map.merge(choice, 1, Integer::sum);
            }
            Integer mostChosenAmount = 0;
            Player mostChosenPlayer = null;
            for (Map.Entry<Player, Integer> entry : map.entrySet()) {
                if (entry.getValue() > mostChosenAmount) {
                    map.remove(mostChosenPlayer);
                    mostChosenAmount = entry.getValue();
                    mostChosenPlayer = entry.getKey();
                }
            }

        }

    }

    @Override
    public void playTurn() {
        ++turn;
        System.out.println("Tour " + turn);
        displayCurrentGameState();

        System.out.println("Le village s'endort");
        // appeller la voyante, lui faire choisir un joueur, et lui donner son rôle
        System.out.println("la voyante se réveille, et désigne un joueur dont elle veut sonder la personnalité");
        Player chosenPlayer = seer.choosePlayer(mediatorState.getPlayers());

        for (Role role : roles) {
            if (role.getPlayer() == chosenPlayer) {
                seer.sendGameInformation("The chosen player has role : " + role.getRoleName());
                break;
            }
        }

        // demander aux loups-garou de voter pour éliminer un joueur
        System.out.println("les Loups-Garous se réveillent, se reconnaissent et désignent une nouvelle victime !!!");


        // faire un vote pour les loups-garous


        // réveiller tout le monde pour qu'ils votent pour l'élimination d'un joueur
        System.out.println("Le village se réveille");


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
        return true;
    }
}
