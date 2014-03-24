package com.tictactoe.view;

import com.tictactoe.model.History;
import com.tictactoe.model.Player;

public interface IGameView {
    void beforeTurn(Player player);
    void onTurn(Player player, int x, int y);  //мы только что сделали ход, необходимо обновить view
    void afterTurn();
    void onStart();
    void showHistory(History history);
    void onWin(Player winner);
    void onDraw();
    void onEnd();
    void onError(String string);
    void onMessage(String string);
}
