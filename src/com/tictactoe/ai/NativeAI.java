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
        H1(0),H2(1),H3(2),V1(0),V2(1),V3(2),X1(0),X2(0);

        private final int param;

        ERow(int param) {
            this.param = param;
        }

        public int getParam() {
            return param;
        }
    }

    private int getRow(ERow row){
        int sum = 0;

        switch (row){
            case H1:
            case H2:
            case H3:
                for (int i = 0; i < size; i++) {
                    sum += U[i][row.getParam()];
                }
                break;
            case V1:
            case V2:
            case V3:
                for (int i = 0; i < size; i++) {
                    sum += U[row.getParam()][i];
                }
                break;
            case X1:
                for (int i = 0; i < size; i++) {
                    //magic function 3+-1=2+-1=1+-1=0 or -1+1=0+1=1+1=2
                    sum += U[2-i][i];
                }
                break;
            case X2:
                for (int i = 0; i < size; i++) {
                    //magic function 3+-1=2+-1=1+-1=0 or -1+1=0+1=1+1=2
                    sum += U[i][i];
                }
                break;
        }
        return sum;
    }

    private boolean isEmpty(int x, int y){
        return getField()[x][y] == Field.DEFAULT_VALUE;
    }

    private boolean win(){
        for(ERow row : ERow.values()){
            if(getRow(row) == -2){
                //we may win
            }
        }
        return false;
    }

    private boolean block(){
        for(ERow row : ERow.values()){
            if(getRow(row) == 2){
                //we may win
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
