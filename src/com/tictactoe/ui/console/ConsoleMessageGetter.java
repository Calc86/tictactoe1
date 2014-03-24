package com.tictactoe.ui.console;

import com.tictactoe.exceptions.NonIntException;
import com.tictactoe.view.MessageGetter;

import java.util.Scanner;

public class ConsoleMessageGetter extends MessageGetter {
    Scanner scanner;

    public ConsoleMessageGetter() {
        this.scanner = new Scanner(System.in);
    }

    public int getInt() throws NonIntException {
        if(scanner.hasNextInt())
            return scanner.nextInt();
        throw new NonIntException(scanner.next());
    }

    public String getString(){
        if(scanner.hasNext())
            return scanner.next();
        return null;
    }
}
