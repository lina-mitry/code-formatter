package com.text.compiler.lexer;

import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.token.IToken;

public interface Lexer {
    IToken nextToken() throws ReaderException, WriterException;

    boolean hasMoreTokens() throws ReaderException;
}
