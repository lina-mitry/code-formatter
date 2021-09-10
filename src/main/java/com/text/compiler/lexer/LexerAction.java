package com.text.compiler.lexer;

import lombok.Data;

@Data
public class LexerAction {
    private Character input;
    private String transition;
    private String command;
}
