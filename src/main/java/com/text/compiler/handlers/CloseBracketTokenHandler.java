package com.text.compiler.handlers;

import com.text.compiler.token.Token;

public class CloseBracketTokenHandler extends TokenHandler {
    public CloseBracketTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token("CLOSE_BRACKET", getLexeme());
    }
}
