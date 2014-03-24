package com.tictactoe;

import com.tictactoe.model.Field;
import com.tictactoe.model.GameModel;
import com.tictactoe.model.Player;
import com.tictactoe.view.IGameInput;
import com.tictactoe.view.IGameView;

public class Game {
    private static Game instance = new Game();

    public static final int PLAYERS_COUNT = 2;
    public static final int MAX_TURNS = 9;

    private Player winner;
    private GameModel model;
    private int turn = 0;

    private IGameView view;
    private IGameInput input;

    private char playerSign[] = {'X','O'};

    public static Game getInstance() {
        return instance;
    }

    private Game(){
        model = GameModel.getInstance();
    }

    public void setView(IGameView view) {
        this.view = view;
    }

    public void setInput(IGameInput input) {
        this.input = input;
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
            model.setPlayer(i,player);
            //ms.setMessage("Введите имя игрока " + player.getSign() + ": ").print();
            player.setName(input.getPlayerName(player));
        }

    }

    private void start(){
        view.onStart();
    }

    public void play(){
        initPlayers();
        start();
        while(!isEnd()){
            getTurn(model.getPlayers()[turn % PLAYERS_COUNT]);
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
        int i = input.getX();
        if(!validateCoordinate(i))
            return getCellX();
        else
            return i;
    }

    private int getCellY(){
        int i = input.getY();
        if(!validateCoordinate(i))
            return getCellX();
        else
            return i;
    }

    private void getTurn(Player player){

        view.beforeTurn(player);
        int x = getCellX();
        int y = getCellY();
        view.onTurn(player, x, y);

        x = x-1;
        y = y-1;

        if(model.getField().isCellSet(x, y)){
            view.onError("Ячейка уже занята, попроубей еще раз");
            getTurn(player);
        }else{
            model.getField().setCell(x, y, player.getSign());
            //insert into history
            model.getHistory().push(model.getField().getField());
            if(isWin())
                winner = player;
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
