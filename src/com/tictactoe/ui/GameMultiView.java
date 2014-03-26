package com.tictactoe.ui;

import com.tictactoe.model.History;
import com.tictactoe.model.Player;
import com.tictactoe.view.IGameView;

import java.util.ArrayList;

public class GameMultiView implements IGameView {

    ArrayList<IGameView> views = new ArrayList<IGameView>();

    public void addView(IGameView view){
        views.add(view);
    }

    @Override
    public void beforeTurn() {
        for(IGameView view : views){
            view.beforeTurn();
        }
    }

    @Override
    public void onTurn() {
        for(IGameView view : views){
            view.onTurn();
        }
    }

    @Override
    public void afterTurn() {
        for(IGameView view : views){
            view.afterTurn();
        }
    }

    @Override
    public void onStart() {
        for(IGameView view : views){
            view.onStart();
        }
    }

    @Override
    public void showHistory(History history) {
        for(IGameView view : views){
            view.showHistory(history);
        }
    }

    @Override
    public void onWin(Player winner) {
        for(IGameView view : views){
            view.onWin(winner);
        }
    }

    @Override
    public void onDraw() {
        for(IGameView view : views){
            view.onDraw();
        }
    }

    @Override
    public void onEnd() {
        for(IGameView view : views){
            view.onEnd();
        }
    }

    @Override
    public void onError(String string) {
        for(IGameView view : views){
            view.onError(string);
        }
    }

    @Override
    public void onMessage(String string) {
        for(IGameView view : views){
            view.onMessage(string);
        }
    }
}
