package com.tictactoe.model;

import com.tictactoe.Game;

public class GameModel {
    volatile private static GameModel instance = new GameModel();

    private Player[] players;
    private History history;
    private TicTacToeField field;

    public static GameModel getInstance() {
        return instance;
    }

    private GameModel() {
        field = TicTacToeField.getInstance();
        players = new Player[Game.PLAYERS_COUNT];
        history = new History(Game.MAX_TURNS,field.size);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayer(int i, Player player){
        players[i] = player;
    }

    public TicTacToeField getField() {
        return field;
    }

    public History getHistory() {
        return history;
    }
}
