import com.tictactoe.Game;
import com.tictactoe.ui.console.*;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        GameConsoleView view = new GameConsoleView();
        game.setView(view);
        game.setInput(view.getInput());

        game.play();
    }
}
