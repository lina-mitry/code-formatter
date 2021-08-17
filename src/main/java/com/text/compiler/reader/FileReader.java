package com.text.compiler.reader;

import java.io.*;
import java.util.Scanner;

public class FileReader implements Reader {
    private final Scanner scanner;

    public FileReader(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
        this.scanner.useDelimiter("");
    }

    @Override
    public boolean hasChars() {
        return scanner.hasNext();
    }

    @Override
    public char readChar() {
        return scanner.next().charAt(0);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
