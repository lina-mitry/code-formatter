package com.text.compiler.enums;

public enum Tokens {
    CLOSE_BRACKET('}'),

    OPEN_BRACKET('{'),

    SEMICOLON(';');

    public final Character label;

    Tokens(Character label) {
        this.label = label;
    }
}
