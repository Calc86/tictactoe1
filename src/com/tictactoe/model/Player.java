package com.tictactoe.model;

import com.tictactoe.ai.RandomAI;
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
        return new RandomAI();
    }

    @Override
    public String toString() {
        return getName();
    }
}
