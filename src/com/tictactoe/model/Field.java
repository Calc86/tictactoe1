package com.tictactoe.model;

public class Field {
    public final int size;
    public static final int DEFAULT_SIZE = 3;
    public static final char DEFAULT_VALUE = ' ';
    private char[][] field;

    public Field(){
        this(DEFAULT_SIZE);
    }

    public Field(int size){
        this.size = size;
        field = new char[size][size];
        clearField();
    }

    public void clearField(){
        for (int i = 0; i < size; i++) {
            clearLine(i);
        }
    }

    public void clearLine(int line){
        for (int i = 0; i < size; i++) {
            clearCell(line,i);
        }
    }

    public void clearCell(int x, int y){
        setCell(x,y,DEFAULT_VALUE);
    }

    public void setCell(int x, int y,char value){
        field[x][y] = value;
    }

    public char getCell(int x, int y){
        return field[x][y];
    }

    public boolean isCellSet(int x, int y){
        return field[x][y] != DEFAULT_VALUE;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
}
