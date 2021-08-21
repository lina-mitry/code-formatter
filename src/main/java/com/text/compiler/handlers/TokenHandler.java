package com.text.compiler.handlers;

import com.text.compiler.lexer.Token;
import lombok.Getter;

@Getter
public abstract class TokenHandler {
    private final String lexeme;

    public TokenHandler(String lexeme) {
        this.lexeme = lexeme;
    }

    public abstract Token handle();
}
