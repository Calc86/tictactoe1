package com.tictactoe.model;

public class History{
    private char[] fields[][];
    private int current = 0;
    private final int size;

    public History(int turns,int fieldSize) {
        this.size = turns;
        fields = new char[this.size][fieldSize][fieldSize];
    }

    public int getCount(){
        return current;
    }

    public void push(char[][] field){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                fields[current][i][j] = field[i][j];
            }
        }
        current++;
    }

    public char[][] pop(){
        return fields[current--];
    }

    public char[][][] getHistory(){
        return fields;
    }
}
