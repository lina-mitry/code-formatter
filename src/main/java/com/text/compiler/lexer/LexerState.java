package com.text.compiler.lexer;

import lombok.Data;

@Data
public class LexerState {
    private final String state;

    public LexerState(String state) {
        this.state = state;
    }
}
