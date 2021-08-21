package com.text.compiler.handlers;

import com.text.compiler.enums.TokenType;
import com.text.compiler.lexer.Token;

public class SemicolonTokenHandler extends TokenHandler {
    public SemicolonTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token(TokenType.SEMICOLON, getLexeme());
    }
}
