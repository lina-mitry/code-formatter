package com.text.compiler.exceptions;

public class CreateCommandException extends RuntimeException {
    public CreateCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateCommandException(String message) {
        super(message);
    }
}
