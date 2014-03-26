package com.tictactoe.ui.gui;

import com.tictactoe.Game;
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

    private JButton[] buttons;

    private JList listMessage;
    private DefaultListModel<String> listMessageModel = new DefaultListModel<String>();

    private final IGameInput input = new GameFormInput();

    private boolean doInput = false;

    //буферный xy для межпроцесья
    private int _x = 0;
    private int _y = 0;
    //private Player currentPlayer;
    private Game game;

    public GameForm() throws HeadlessException {
        super("TicTacToe");

        game = Game.getInstance();
        buttons = new JButton[Game.MAX_TURNS];

        setContentPane(pTicTacToe);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listMessage.setLayoutOrientation(JList.VERTICAL);
        listMessage.setVisibleRowCount(0);
        listMessage.setModel(listMessageModel);

        buttons[0] = b1;
        buttons[1] = b2;
        buttons[2] = b3;
        buttons[3] = b4;
        buttons[4] = b5;
        buttons[5] = b6;
        buttons[6] = b7;
        buttons[7] = b8;
        buttons[8] = b9;

        for (int i = 0; i < Game.MAX_TURNS; i++) {
            addButtonListener(buttons[i],i+1);
        }

        setVisible(true);
    }

    class ButtonListener implements ActionListener{
        protected final JButton button;
        protected final int cell;

        ButtonListener(JButton button, int cell) {
            this.button = button;
            this.cell = cell;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonListener(cell);
        }
    }

    private void addButtonListener(final JButton button, final int cell){
        button.addActionListener(new ButtonListener(button,cell));
    }

    public void buttonListener(int cell){
        switch (cell){
            case 1: _x = 1; _y = 1; break;
            case 2: _x = 2; _y = 1; break;
            case 3: _x = 3; _y = 1; break;
            case 4: _x = 1; _y = 2; break;
            case 5: _x = 2; _y = 2; break;
            case 6: _x = 3; _y = 2; break;
            case 7: _x = 1; _y = 3; break;
            case 8: _x = 2; _y = 3; break;
            case 9: _x = 3; _y = 3; break;
        }
        doInput = true;
    }

    @Override
    public void beforeTurn() {
        listMessageModel.add(0,"Ход " + game.getCurrentPlayer().getName());
    }

    @Override
    public void onTurn() {
        onMessage(game.getCurrentPlayer().getName() + ": ячейка " + game.getCurrentPlayer().getX() + "," + game.getCurrentPlayer().getY());
    }

    @Override
    public void afterTurn() {
        //disable button
        int buttonIndex = (game.getCurrentPlayer().getX()-1) + (game.getCurrentPlayer().getY()-1) * 3;
        String name = "" + game.getCurrentPlayer().getSign();
        buttons[buttonIndex].setText(name);
        buttons[buttonIndex].setEnabled(false);
    }

    @Override
    public void onStart() {
        listMessageModel.add(0,"Игра запущена");
    }

    @Override
    public void showHistory(History history) {
        //really do nothing, because i dont now what we do;
    }

    @Override
    public void onWin(Player winner) {
        JOptionPane.showMessageDialog(this,winner + " выиграл");
        //System.exit(0);
    }

    @Override
    public void onDraw() {
        JOptionPane.showMessageDialog(this,"НИЧЬЯ");
        //System.exit(0);
    }

    @Override
    public void onEnd() {
        onMessage("Игра закончена");
        for (int i = 0; i < Game.MAX_TURNS; i++) {
            buttons[i].setEnabled(false);
        }
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

    private String inputPlayerName(){
        return JOptionPane.showInputDialog(this, "Введите имя для игрока " + game.getCurrentPlayer().getSign());
    }

    class GameFormInput implements IGameInput{
        @Override
        public int getX() {
            doInput = false;
            waitInput();    //sleep main thread
            return _x;
        }

        @Override
        public int getY() {
            doInput = false;    //do next wait
            return _y;
        }

        @Override
        public String getPlayerName(Player player) {
            return inputPlayerName();
        }
    }
}
