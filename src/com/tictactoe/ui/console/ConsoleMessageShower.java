package com.tictactoe.ui.console;

import com.tictactoe.view.MessageShower;

public class ConsoleMessageShower extends MessageShower {

    @Override
    public void show() {
        System.out.println(getMessage());
    }

    @Override
    public void print() {
        System.out.print(getMessage());
    }
}
