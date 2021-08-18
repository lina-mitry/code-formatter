package com.text.compiler.writer;

import com.text.compiler.exceptions.WriterException;

public interface Writer extends AutoCloseable {
    void writeChar(char symbol) throws WriterException;
}
