package com.tictactoe.model;

public class TicTacToeField extends Field {

    public static final int SIZE = 3;
    private static TicTacToeField instance = new TicTacToeField();

    private TicTacToeField() {
        super(SIZE);
    }

    public static TicTacToeField getInstance() {
        return instance;
    }
}
