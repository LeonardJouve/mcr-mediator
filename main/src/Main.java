
import mediator.BaseRuleMediator;
import mediator.Mediator;
import player.Player;
import ui.ConsoleDisplay;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>(List.of(
            new Player("Alizée"),
            new Player("Guillermo"),
            new Player("Felicia"),
            new Player("Olivier"),
            new Player("Ousmane"),
            new Player("Alireza"),
            new Player("Fabien"),
            new Player("Elise")
        ));
        Mediator mediator = new BaseRuleMediator(players, new ConsoleDisplay());
        while(!mediator.isGameOver()){
            mediator.playTurn();
        }
        System.out.println("Finished");
    }
}
