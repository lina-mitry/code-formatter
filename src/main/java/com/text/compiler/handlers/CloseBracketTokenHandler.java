package com.text.compiler.handlers;

import com.text.compiler.enums.TokenType;
import com.text.compiler.lexer.Token;

public class CloseBracketTokenHandler extends TokenHandler {
    public CloseBracketTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token(TokenType.CLOSE_BRACKET, getLexeme());
    }
}
