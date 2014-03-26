import com.tictactoe.Game;
import com.tictactoe.ui.console.*;
import com.tictactoe.ui.gui.GameButtonView;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();

        GameConsoleView view = new GameConsoleView();
        game.setView(view);
        //game.setInput(view.getInput(),view.getInput());
        game.setInput(view.getInput(),null);

        //GameButtonView buttonView = new GameButtonView();
        //game.setView(buttonView);
        //game.setInput(buttonView.getInput(),null);

        game.play();
    }
}
