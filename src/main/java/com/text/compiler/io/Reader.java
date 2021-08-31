package com.text.compiler.io;

import com.text.compiler.exceptions.ReaderException;

public interface Reader extends Closable {

    boolean hasChars() throws ReaderException;

    Character readChar() throws ReaderException;
}
