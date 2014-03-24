package com.tictactoe.view;

import com.tictactoe.model.Player;

public interface IGameInput {
    int getX();
    int getY();
    String getPlayerName(Player player);
}
