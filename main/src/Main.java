
import mediator.BaseRuleMediator;
import mediator.Mediator;
import player.Player;
import ui.ConsoleDisplay;


public class Main {
    public static void main(String[] args) {
        DevStuff devStuff = new DevStuff();
        Mediator mediator = new BaseRuleMediator(devStuff.players, new ConsoleDisplay());
        while(!mediator.isGameOver()){
            mediator.playTurn();
        }
        System.out.println("Finished");
    }
}
