package com.text.compiler.reader;

public interface Reader extends AutoCloseable{
    boolean hasChars();

    char readChar();
}
