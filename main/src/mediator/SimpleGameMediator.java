package mediator;

import player.Player;
import role.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;


// pour des parties de type "III distribution des cartes pour parties simplifiées" : https://www.regledujeu.fr/loup-garou-regle/

public class SimpleGameMediator implements Mediator{
    private final MediatorState mediatorState;
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private Seer seer = null;

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
    }

    public void broadcastMessage(String message) {

    }

    @Override
    public void playTurn() {
        ++turn;
        System.out.println("Tour " + turn);
        // appeller la voyante, lui faire choisir un joueur, et lui donner son rôle
        System.out.println("la voyante et lui demander de désigner un joueur dont elle veut sonder la personnalité");

        // demander aux loups-garou de voter pour éliminer un joueur
        System.out.println("les Loups-Garous se réveillent, se reconnaissent et désignent une nouvelle victime !!!");


        // réveiller tout le monde pour qu'ils votent pour l'élimination d'un joueur
        System.out.println("Le village se réveille");

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

        return true;
    }
}
