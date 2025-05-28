import mediator.MediatorState;
import mediator.NormalMediator;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MediatorState mediatorState = new MediatorState();
        new MainFrame(mediatorState);
    }
}
