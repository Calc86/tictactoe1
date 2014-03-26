import com.tictactoe.Game;
import com.tictactoe.ai.NativeAI;
import com.tictactoe.ui.GameMultiView;
import com.tictactoe.ui.console.*;
import com.tictactoe.ui.gui.GameButtonView;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        NativeAI ai = new NativeAI();

        GameConsoleView consoleView = new GameConsoleView();
        //game.setView(view);
        //game.setInput(view.getInput(),view.getInput());
        //game.setInput(view.getInput(),null);

        GameButtonView buttonView = new GameButtonView();
        //game.setView(buttonView);
        //game.setInput(buttonView.getInput(),buttonView.getInput());
        //game.setInput(buttonView.getInput(),ai);

        GameMultiView gameMultiView = new GameMultiView();
        gameMultiView.addView(consoleView);
        gameMultiView.addView(buttonView);
        game.setView(gameMultiView);
        game.setInput(buttonView.getInput(),ai);

        game.play();
    }
}
