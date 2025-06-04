
import mediator.MediatorState;
import mediator.BaseRuleMediator;
import player.Player;


public class Main {
    public static void main(String[] args) {

        MediatorState mediatorState = new MediatorState();
        //new MainFrame(mediatorState);

        mediatorState.setGameMediator(new BaseRuleMediator(mediatorState));

        DevStuff devStuff = new DevStuff();
        for (Player p : devStuff.players){
            mediatorState.addPlayer(p);
        }
        mediatorState.start();
    }
}
