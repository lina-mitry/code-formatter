package com.text.compiler.handlers;

import com.text.compiler.token.Token;

public class OtherTokenHandler extends TokenHandler {
    public OtherTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token("OTHER", getLexeme());
    }
}
