package com.text.compiler.lexer;

import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.token.IToken;

public interface Lexer {
    IToken nextToken() throws ReaderException;

    boolean hasMoreTokens() throws ReaderException;
}
