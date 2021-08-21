package com.text.compiler.lexer;

import com.text.compiler.enums.TokenType;

public class Token {
    public final TokenType type;
    public final String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public String toString() {
        return type + ": " + content;
    }
}
