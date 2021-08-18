package com.text.compiler.reader;

import com.text.compiler.exceptions.ReaderException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReader implements Reader {
    private final Scanner scanner;

    public FileReader(File file) throws ReaderException {
        try {
            this.scanner = new Scanner(file).useDelimiter("");
        } catch (FileNotFoundException e) {
            throw new ReaderException("Failed to initialize reader", e);
        }
    }

    @Override
    public boolean hasChars() throws ReaderException {
        try {
            return scanner.hasNext();
        } catch (IllegalStateException e) {
            throw new ReaderException("Failed to check is char exists", e);
        }
    }

    @Override
    public char readChar() throws ReaderException {
        try {
            return scanner.next().charAt(0);
        } catch (RuntimeException e) {
            throw new ReaderException("Failed to read next symbol", e);
        }

    }

    @Override
    public void close() throws ReaderException {
        try {
            scanner.close();
        } catch (IllegalStateException e) {
            throw new ReaderException("Failed to close scanner", e);
        }
    }
}
