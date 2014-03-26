package com.tictactoe.ui.console;

import com.tictactoe.*;
import com.tictactoe.exceptions.NonIntException;
import com.tictactoe.model.GameModel;
import com.tictactoe.model.History;
import com.tictactoe.model.Player;
import com.tictactoe.model.TicTacToeField;
import com.tictactoe.view.MessageGetter;
import com.tictactoe.view.MessageShower;
import com.tictactoe.view.IGameInput;
import com.tictactoe.view.IGameView;
import com.util.Environment;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class GameConsoleView implements IGameView {
    TicTacToeField field;
    MessageShower ms;
    Game game;
    GameConsoleInput input;
    GameModel model;

    public GameConsoleView() {
        this.field = TicTacToeField.getInstance();
        ms = new ConsoleMessageShower();
        game = Game.getInstance();
        model = GameModel.getInstance();

        setEncoding();
    }

    private void setEncoding(){
        String encoding = "cp1251";

        //if(Environment.isWin()) encoding = "Cp866";
        if(Environment.isWin()) encoding = "utf8";
        else if (Environment.isMac()) encoding = "utf8";
        else if (Environment.isUnix()) encoding = "utf8";

        message(Environment.getOsName() + " " + Environment.getOsVersion());
        try{
            System.setOut(new PrintStream(System.out, true, encoding));
        }catch (UnsupportedEncodingException e){
            message("cant set encoding for our");
        }
        try{
            System.setErr(new PrintStream(System.out, true, encoding));
        }catch (UnsupportedEncodingException e){
            message("cant set encoding for err");
        }
    }

    @Override
    public void beforeTurn() {
        Player player = game.getCurrentPlayer();
        message("Ход игрока " + player.getName() + "(" + player.getSign() + ")");
    }

    @Override
    public void onTurn() {
        Player player = game.getCurrentPlayer();
        //, int x, int y;
        message("Вы ввели: " + player.getX() + " " + player.getY());
    }

    @Override
    public void afterTurn() {
        showField();
    }

    @Override
    public void onStart() {
        message("Игра запущена");
        message("мы играем с:");

        for(Player player : model.getPlayers()){
            message(player.getSign() + ": " + player.getName());
        }

        message("we have field:");

        showField();
    }

    @Override
    public void showHistory(History history) {
        char[][][] all = history.getHistory();

        ms.setMessage("История:").show();
        for (int i = 0; i < history.getCount(); i++) {
            field.setField(all[i]);
            showField();
            message("");
        }
    }

    @Override
    public void onWin(Player winner) {
        message("Победитель: " + winner + "(" + winner.getSign() + ")");
    }

    @Override
    public void onDraw() {
        message("НИЧЬЯ");
    }

    @Override
    public void onEnd() {
        message("Игра закончена");
    }

    @Override
    public void onMessage(String string) {
        message(string);
    }

    @Override
    public void onError(String string) {
        message(string);
    }

    private void message(String string){
        ms.setMessage(string).show();
    }

    private void print(String string){
        ms.setMessage(string).print();
    }

    public GameConsoleInput getInput(){
        if(input == null) input = new GameConsoleInput();
        return input;
    }

    private void showLine(int line){
        for (int i = 0; i < field.size; i++) {
            showCell(i,line);
        }
    }

    public void showField(){
        message(" X=[1][2][3]");
        for (int i = 0; i < field.size; i++) {
            print("[" + (i + 1) + "]");
            showLine(i);
            message("");
        }
    }

    private void showCell(int x, int y){
        print("[" + field.getField()[x][y] + "]");
    }

    class GameConsoleInput implements IGameInput {
        private MessageGetter mg;

        GameConsoleInput() {
            this.mg = new ConsoleMessageGetter();
        }

        @Override
        public int getX() {
            return getInt("Введите координату клетки по оси X: ");
        }

        @Override
        public int getY() {
            return getInt("Введите координату клетки по оси Y: ");
        }

        @Override
        public String getPlayerName(Player player) {
            message("Введите имя игрока " + player.getSign() + ": ");
            return mg.getString();
        }

        private int getInt(String message){
            try{
                onMessage(message);
                return mg.getInt();
            } catch (NonIntException e){
                onError("Вы ввели какую то херню(" + e.getMessage() + "), повторите попытку");
                return getInt(message);
            }
        }
    }
}
