package com.text.compiler.handlers;

import com.text.compiler.enums.TokenType;
import com.text.compiler.lexer.Token;

public class OpenBracketTokenHandler extends TokenHandler {
    public OpenBracketTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token(TokenType.OPEN_BRACKET, String.valueOf(getLexeme()));
    }
}
