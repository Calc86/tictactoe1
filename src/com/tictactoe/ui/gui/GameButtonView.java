package com.tictactoe.ui.gui;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.tictactoe.model.History;
import com.tictactoe.model.Player;
import com.tictactoe.view.IGameInput;
import com.tictactoe.view.IGameView;
import sun.net.www.content.text.plain;

import javax.swing.*;

/**
 * Created by calc on 26.03.14.
 */
public class GameButtonView implements IGameView {

    private GameForm gameForm;
    public GameButtonView() {
        //запускем окно
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            //do nothing... e.printStackTrace();
        }
        gameForm = new GameForm();
    }

    @Override
    public void beforeTurn() {
        gameForm.beforeTurn();
    }

    @Override
    public void onTurn() {
        gameForm.onTurn();
    }

    @Override
    public void afterTurn() {
        gameForm.afterTurn();
    }

    @Override
    public void onStart() {
        gameForm.onStart();
    }

    @Override
    public void showHistory(History history) {
        gameForm.showHistory(history);
    }

    @Override
    public void onWin(Player winner) {
        gameForm.onWin(winner);
    }

    @Override
    public void onDraw() {
        gameForm.onDraw();
    }

    @Override
    public void onEnd() {
        gameForm.onEnd();
    }

    @Override
    public void onError(String string) {
        gameForm.onError(string);
    }

    @Override
    public void onMessage(String string) {
        gameForm.onMessage(string);
    }

    public IGameInput getInput(){
        return gameForm.getInput();
    }
}
