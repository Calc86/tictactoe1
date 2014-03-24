package com.tictactoe.model;

public class Player {

    private String name;
    private final char sign;

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

    @Override
    public String toString() {
        return getName();
    }
}
