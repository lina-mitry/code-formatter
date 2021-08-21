package com.text.compiler.handlers;

import com.text.compiler.enums.TokenType;
import com.text.compiler.lexer.Token;

public class OtherTokenHandler extends TokenHandler {
    public OtherTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token(TokenType.OTHER, getLexeme());
    }
}
