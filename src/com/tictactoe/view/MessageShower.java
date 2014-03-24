package com.tictactoe.view;

public abstract class MessageShower {

    private String message;

    public MessageShower(String message) {
        this.message = message;
    }

    protected MessageShower() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    public MessageShower setMessage(String message) {
        this.message = message;
        return this;
    }

    public abstract void show();
    public abstract void print();
}
