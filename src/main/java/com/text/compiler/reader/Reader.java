package com.text.compiler.reader;

import com.text.compiler.exceptions.ReaderException;

public interface Reader extends AutoCloseable {
    boolean hasChars() throws ReaderException;

    char readChar() throws ReaderException;
}
