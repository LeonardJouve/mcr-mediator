
import mediator.BaseRuleMediator;
import mediator.Mediator;
import player.Player;


public class Main {
    public static void main(String[] args) {
        DevStuff devStuff = new DevStuff();
        Mediator mediator = new BaseRuleMediator(devStuff.players);
        while(!mediator.isGameOver()){
            mediator.playTurn();
        }
        System.out.println("Finished");
    }
}
