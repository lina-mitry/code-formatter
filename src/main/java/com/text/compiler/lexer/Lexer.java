package com.text.compiler.lexer;

import com.text.compiler.token.IToken;

public interface Lexer {
    IToken readToken();

    boolean hasMoreTokens();
}
