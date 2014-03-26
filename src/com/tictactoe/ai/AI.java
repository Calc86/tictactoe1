package com.tictactoe.ai;

import com.tictactoe.model.Player;
import com.tictactoe.view.IGameInput;

/**
 * Created by calc on 26.03.14.
 */
public abstract class AI implements IGameInput{
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getPlayerName(Player player) {
        Integer ai = (int)(Math.random() * 100);
        return getName() + "-" + ai.toString();
    }
}
