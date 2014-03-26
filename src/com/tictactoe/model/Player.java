package com.tictactoe.model;

import com.tictactoe.view.IGameInput;

public class Player {

    private String name;
    private final char sign;
    private IGameInput input;

    int x, y;

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

    public int inputX(){
        x = input.getX();
        return x;
    }

    public int inputY(){
        y = input.getY();
        return y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String inputPlayerName(){
        setName(input.getPlayerName(this));
        return getName();
    }

    /*public IGameInput getInput() {
        return input;
    }*/

    public void setInput(IGameInput input) {
        if(input != null)
            this.input = input;
        else
            this.input = aiInput();
    }

    private IGameInput aiInput(){
        return new IGameInput() {
            @Override
            public int getX() {
                return (int)(Math.random() * 3 + 1);
            }

            @Override
            public int getY() {
                return (int)(Math.random() * 3 + 1);
            }

            @Override
            public String getPlayerName(Player player) {
                Integer ai = (int)(Math.random() * 100);
                return "Ai-" + ai.toString();
            }
        };
    }

    @Override
    public String toString() {
        return getName();
    }
}
