package com.text.compiler.lexer;

import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.token.Token;

public interface Lexer {
    Token nextToken() throws ReaderException;

    boolean hasMoreTokens() throws ReaderException;
}
