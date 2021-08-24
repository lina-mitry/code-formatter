package com.text.compiler.handlers;

import com.text.compiler.token.Token;

public class SemicolonTokenHandler extends TokenHandler {
    public SemicolonTokenHandler(String lexeme) {
        super(lexeme);
    }

    @Override
    public Token handle() {
        return new Token("SEMICOLON", getLexeme());
    }
}
