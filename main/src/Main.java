
import mediator.BaseRuleMediator;
import mediator.Mediator;
import player.Player;
import ui.GameDisplay;
import ui.graphical.MainFrame;
import ui.UserInput;
import ui.graphical.GraphicalDisplay;
import ui.graphical.GraphicalInput;

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
        MainFrame frame = new MainFrame();
        Mediator mediator = new BaseRuleMediator(players, frame.getDisplay());

        frame.setVisible(true);
        mediator.start();
        System.out.println("Finished");
    }
}
