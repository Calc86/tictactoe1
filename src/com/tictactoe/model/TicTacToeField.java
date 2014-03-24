package com.tictactoe.model;

public class TicTacToeField extends Field {

    public static final int SIZE = 3;
    private static TicTacToeField instance;

    private TicTacToeField() {
        super(SIZE);
    }

    public static TicTacToeField getInstance() {
        if(instance==null)
            instance = new TicTacToeField();
        return instance;
    }
}
