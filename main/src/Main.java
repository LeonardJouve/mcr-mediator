
import mediator.alternative.BaseRuleMediator;
import mediator.Mediator;
import player.Player;
import ui.RandomInput;
import ui.graphical.MainFrame;

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
        // Auto graphical
        // --------------------------------------
        MainFrame autoframe = new MainFrame(new RandomInput());
        Mediator automediator = new BaseRuleMediator(players, autoframe.getDisplay());
        autoframe.setVisible(true);
        automediator.start();
        // --------------------------------------

        // Manual graphical
        // --------------------------------------
//        MainFrame manualframe = new MainFrame();
//        Mediator manualmediator = new BaseRuleMediator(players, manualframe.getDisplay());
//        manualframe.setVisible(true);
//        manualmediator.start();
        // --------------------------------------

        // Auto console
        // --------------------------------------
//        GameDisplay display = new ConsoleDisplay(new RandomInput());
//        Mediator autoMediator = new BaseRuleMediator(players, display);
//        autoMediator.start();
        // --------------------------------------

        System.out.println("Finished");
    }
}
