package com.tictactoe.ui.gui;

import com.tictactoe.model.History;
import com.tictactoe.model.Player;
import com.tictactoe.view.IGameInput;
import com.tictactoe.view.IGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameForm extends JFrame implements IGameView{
    private JPanel pTicTacToe;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JList listMessage;
    private DefaultListModel<String> listMessageModel = new DefaultListModel<String>();

    private final IGameInput input = new GameFormInput();

    private boolean doInput = false;
    private int x = 0;
    private int y = 0;

    public GameForm() throws HeadlessException {
        super("TicTacToe");
        setContentPane(pTicTacToe);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listMessage.setLayoutOrientation(JList.VERTICAL);
        listMessage.setVisibleRowCount(0);
        listMessage.setModel(listMessageModel);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(1);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b2.getName()));
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b3.getName()));
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b4.getName()));
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b5.getName()));
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b6.getName()));
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b7.getName()));
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b8.getName()));
            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListener(getCell(b9.getName().toString()));
            }
        });

        setVisible(true);
    }

    public void buttonListener(int cell){
        switch (cell){
            case 1: x = 1; y = 1; break;
            case 2: x = 2; y = 1; break;
            case 3: x = 3; y = 1; break;
            case 4: x = 1; y = 2; break;
            case 5: x = 2; y = 2; break;
            case 6: x = 3; y = 2; break;
            case 7: x = 1; y = 3; break;
            case 8: x = 2; y = 3; break;
            case 9: x = 3; y = 3; break;
        }
        doInput = true;
    }

    private int getCell(String s){
        return Integer.parseInt(s);
    }

    @Override
    public void beforeTurn(Player player) {
        listMessageModel.add(0,"Ваш ход " + player.getName());
        waitInput();    //sleep main thread
    }

    @Override
    public void onTurn(Player player, int x, int y) {

    }

    @Override
    public void afterTurn() {

    }

    @Override
    public void onStart() {
        listMessageModel.add(0,"Игра запущена");
    }

    @Override
    public void showHistory(History history) {

    }

    @Override
    public void onWin(Player winner) {

    }

    @Override
    public void onDraw() {

    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onError(String string) {
        listMessageModel.add(0,string.toUpperCase());
    }

    @Override
    public void onMessage(String string) {
        listMessageModel.add(0,string);
    }

    public IGameInput getInput(){
        return input;
    }

    public void waitInput(){
        while(!doInput){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                //do nothing e.printStackTrace();
            }
        }
    }

    class GameFormInput implements IGameInput{
        @Override
        public int getX() {
            doInput = false;
            return x;
        }

        @Override
        public int getY() {
            doInput = false;    //do next wait
            return y;
        }

        @Override
        public String getPlayerName(Player player) {
            return "name";
        }
    }
}
