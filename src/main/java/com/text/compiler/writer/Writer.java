package com.text.compiler.writer;

import java.io.IOException;

public interface Writer extends AutoCloseable {
    void writeChar(char symbol) throws IOException;
}
