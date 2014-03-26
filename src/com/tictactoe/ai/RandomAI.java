package com.tictactoe.ai;

/**
 * Created by calc on 26.03.14.
 */
public class RandomAI extends AI {
    @Override
    public int getX() {
        return (int)(Math.random() * 3 + 1);
    }

    @Override
    public int getY() {
        return (int)(Math.random() * 3 + 1);
    }
}
