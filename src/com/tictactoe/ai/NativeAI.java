package com.tictactoe.ai;

import com.tictactoe.Game;
import com.tictactoe.model.Field;
import com.tictactoe.model.GameModel;
import com.tictactoe.model.Player;
import com.tictactoe.model.TicTacToeField;

public class NativeAI extends AI {
    int x,y;
    int size = TicTacToeField.SIZE;
    int[][] U = {
            {1,0,1},
            {0,3,0},
            {1,0,1},
    };

    private void calculate(){
        Game game = Game.getInstance();
        Player player = game.getCurrentPlayer();
        char sign = player.getSign();

        excludeOccupiedCells();


        int max = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(U[i][j]>max){
                    x = i+1;
                    y = j+1;
                    max = U[i][j];
                }
            }
        }
    }

    private char[][] getField(){
        return GameModel.getInstance().getField().getField();
    }

    private void excludeOccupiedCells(){
        char[][] field = getField();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(field[i][j] != Field.DEFAULT_VALUE)
                    U[i][j] = 0;
            }
        }
    }

    @Override
    public int getX() {
        //вычисляем X Y
        calculate();
        return x;
    }

    @Override
    public int getY() {
        //Возвращаем Y
        return y;
    }
}
