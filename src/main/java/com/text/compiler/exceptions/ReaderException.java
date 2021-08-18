package com.text.compiler.exceptions;

import java.io.IOException;

public class ReaderException extends IOException {
    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderException(String message) {
        super(message);
    }
}
