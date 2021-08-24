package com.text.compiler.handlers;

import com.text.compiler.token.Token;

public class OpenBracketTokenHandler extends TokenHandler {
    public OpenBracketTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token("OPEN_BRACKET", getLexeme());
    }
}
