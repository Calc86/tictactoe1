package com.tictactoe.view;

import com.tictactoe.exceptions.*;

public abstract class MessageGetter {
    public abstract int getInt() throws NonIntException;
    public abstract String getString();
}


