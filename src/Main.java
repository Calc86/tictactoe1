import com.tictactoe.Game;
import com.tictactoe.ui.console.*;
import com.tictactoe.ui.gui.GameButtonView;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        GameConsoleView view = new GameConsoleView();
        //game.setView(view);
        GameButtonView buttonView = new GameButtonView();

        game.setView(buttonView);
        //game.setInput(view.getInput());
        game.setInput(buttonView.getInput());

        game.play();
    }
}
