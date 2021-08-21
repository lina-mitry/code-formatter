package com.text.compiler.io;

import com.text.compiler.exceptions.WriterException;

public interface Writer extends Closable {
    void writeChar(char symbol) throws WriterException;
}
