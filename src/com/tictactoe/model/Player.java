package com.tictactoe.model;

import com.tictactoe.view.IGameInput;

public class Player {

    private String name;
    private final char sign;
    private IGameInput input;

    public Player(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IGameInput getInput() {
        return input;
    }

    public void setInput(IGameInput input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return getName();
    }
}
