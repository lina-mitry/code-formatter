package com.text.compiler.io;

import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;

public interface Closable extends AutoCloseable {
    @Override
    void close() throws CloseException, ReaderException, WriterException;
}
