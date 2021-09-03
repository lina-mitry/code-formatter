package com.text.compiler.command;

public interface Command<T, C> {
    void execute(T token, C context);
}
