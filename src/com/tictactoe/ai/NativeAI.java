package com.tictactoe.ai;

import com.tictactoe.Game;
import com.tictactoe.model.Field;
import com.tictactoe.model.GameModel;
import com.tictactoe.model.TicTacToeField;

public class NativeAI extends AI {
    int x,y;
    int size = TicTacToeField.SIZE;
    int[][] U = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    enum ERow{
        H1(0,0),H2(1,1),H3(2,2),V1(0,3),V2(1,4),V3(2,5),X1(0,6),X2(0,7);

        int[][] xs = {
                {0,1,2},
                {0,1,2},
                {0,1,2},
                {0,0,0},
                {1,1,1},
                {2,2,2},
                {0,1,2},
                {0,1,2},
        };

        int[][] ys = {
                {0,0,0},
                {1,1,1},
                {2,2,2},
                {0,1,2},
                {0,1,2},
                {0,1,2},
                {0,1,2},
                {2,1,0},
        };

        private final int param;
        private final int index;

        ERow(int param, int index) {
            this.param = param;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public int getParam() {
            return param;
        }

        public int[] getXs(){ return xs[getIndex()]; }
        public int[] getYs(){ return ys[getIndex()]; }
    }

    private int getRow(ERow row){
        int sum = 0;

        for (int i = 0; i < size; i++) {
            sum += U[row.getXs()[i]][row.getYs()[i]];
        }

        return sum;
    }

    private boolean getFreeCell(ERow row){
        int i =0;
        for (i = 0; i < size; i++) {
            if(U[row.getXs()[i]][row.getYs()[i]] == 0)
                break;
        }
        if(i<size){
            x = row.getXs()[i];
            y = row.getYs()[i];
            System.out.println("free " + x + ":" + y);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isEmpty(int x, int y){
        return getField()[x][y] == Field.DEFAULT_VALUE;
    }

    private boolean win(){
        for(ERow row : ERow.values()){
            if(getRow(row) == -2){
                //we may win
                System.out.println("we may win!");
                return getFreeCell(row);
            }
        }
        return false;
    }

    private boolean block(){
        for(ERow row : ERow.values()){
            if(getRow(row) == 2){
                //we need block
                System.out.println("we need block");
                return getFreeCell(row);
            }
        }
        return false;
    }

    private boolean center(){
        if(isEmpty(1,1)){
            x = 1;
            y = 1;
            return true;
        }
        else{
            return false;
        }
    }

    private boolean corner(int i){
        int[][] corners = {
                {0,0},
                {0,2},
                {2,0},
                {2,2},
        };

        if(i<corners.length){
            if(isEmpty(corners[i][0],corners[i][1])){
                x = corners[i][0];
                y = corners[i][1];
                return true;
            }
            else
            {
                return corner(++i);
            }
        }
        else{
            return false;
        }
    }

    private boolean random(){
        x = (int)(Math.random() * 3);
        y = (int)(Math.random() * 3);
        if(isEmpty(x,y))
            return true;
        else
            return random();
    }

    private void calculate(){
        Game game = Game.getInstance();

        U[game.getLastX()-1][game.getLastY()-1] = 1;

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(U[j][i] + " ");
//            }
//            System.out.println("");
//        }

        if(!win())
            if(!block())
                if(!center())
                    if(!corner(0))
                        random();
        U[x][y] = -1;
        x++; y++;
    }

    private char[][] getField(){
        return GameModel.getInstance().getField().getField();
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
