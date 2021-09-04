package com.text.compiler.lexer;

import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.token.IToken;

public interface Lexer {
    IToken nextToken() throws ReaderException, CloseException, WriterException;

    boolean hasMoreTokens() throws ReaderException;
}
