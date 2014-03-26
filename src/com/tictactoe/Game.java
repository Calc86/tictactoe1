package com.tictactoe;

import com.tictactoe.model.Field;
import com.tictactoe.model.GameModel;
import com.tictactoe.model.Player;
import com.tictactoe.view.IGameInput;
import com.tictactoe.view.IGameView;

public class Game {
    volatile private static Game instance = new Game();

    public static final int PLAYERS_COUNT = 2;
    public static final int MAX_TURNS = 9;

    private Player winner;  // используется для проверки на выигрышь

    private GameModel model;
    private int turn = 0;
    private Player currentPlayer;

    private IGameView view;
    private IGameInput[] input = new IGameInput[PLAYERS_COUNT];

    private char playerSign[] = {'X','O'};

    public static Game getInstance() {
        return instance;
    }

    private int lastX, lastY;

    private Game(){
        model = GameModel.getInstance();
    }

    public void setView(IGameView view) {
        this.view = view;
    }

    public void setInput(IGameInput input1, IGameInput input2) {
        this.input[0] = input1;
        this.input[1] = input2;
    }

    private boolean isEnd() {
        return (winner!=null) || turn >= MAX_TURNS;
    }

    private boolean isDraw() {
        return winner == null;
    }

    private void initPlayers(){
        for (int i = 0; i < PLAYERS_COUNT; i++) {
            Player player = new Player(playerSign[i]);
            currentPlayer = player;
            player.setInput(input[i]);
            model.setPlayer(i,player);
            player.inputPlayerName();
        }

    }

    private void start(){
        view.onStart();
    }

    public void play(){
        initPlayers();
        start();
        while(!isEnd()){
            currentPlayer = model.getPlayers()[turn % PLAYERS_COUNT];
            getTurn();
            view.afterTurn();
        }
        showHistory();
        showStatus();
    }

    private boolean validateCoordinate(int i){
        if(i <= 0 || i> model.getField().size){
            view.onError("Не верное значение, значение должно быть от 1 до " + model.getField().size + " попробуйте еще раз");
            return false;
        }
        return true;
    }

    private int getCellX(){
        Player player = getCurrentPlayer();

        int i = player.inputX();
        if(!validateCoordinate(i))
            return getCellX();
        else
            return i;
    }

    private int getCellY(){
        Player player = getCurrentPlayer();

        int i = player.inputY();
        if(!validateCoordinate(i))
            return getCellY();
        else
            return i;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    private void getTurn(){
        view.beforeTurn();
        lastX = getCellX();
        lastY = getCellY();
        view.onTurn();

        int x = lastX-1;
        int y = lastY-1;

        if(model.getField().isCellSet(x, y)){
            view.onError("Ячейка уже занята, попроубей еще раз");
            getTurn();
        }else{
            model.getField().setCell(x, y, getCurrentPlayer().getSign());
            //insert into history
            model.getHistory().push(model.getField().getField());
            if(isWin())
                winner = getCurrentPlayer();
            turn++;
        }
    }

    private boolean isLineWin(int line){
        if(model.getField().getCell(0,line)== Field.DEFAULT_VALUE) return false;
        if(model.getField().getCell(0, line) == model.getField().getCell(1, line) &&
                model.getField().getCell(0, line) == model.getField().getCell(2, line))
            return true;
        else
            return false;
    }

    private boolean isColWin(int col){
        if(model.getField().getCell(col, 0)==Field.DEFAULT_VALUE) return false;
        if(model.getField().getCell(col, 0) == model.getField().getCell(col, 1) &&
                model.getField().getCell(col, 0) == model.getField().getCell(col, 2))
            return true;
        else
            return false;
    }

    private boolean isCrossWin(){
        if(model.getField().getCell(1, 1)==Field.DEFAULT_VALUE) return false;
        if(
                (model.getField().getCell(1, 1) == model.getField().getCell(0, 0) &&
                model.getField().getCell(1, 1) == model.getField().getCell(2, 2)) ||
                (model.getField().getCell(1, 1) == model.getField().getCell(2, 0) &&
                model.getField().getCell(1, 1) == model.getField().getCell(0, 2))
                )
            return true;
        else return false;
    }


    private boolean isWin(){
        for (int i = 0; i < model.getField().size; i++) {
            if(isColWin(i)) return true;
            if(isLineWin(i)) return true;
        }

        if(isCrossWin()) return true;

        if(turn < MAX_TURNS)
            return false;
        else
            return true;
    }

    private void showStatus(){
        view.onEnd();
        if(isDraw())
            view.onDraw();
        else
            view.onWin(winner);
    }

    private void showHistory(){
        view.showHistory(model.getHistory());
    }
}
