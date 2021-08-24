package com.text.compiler.io;

import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReader implements Reader {

    private final InputStreamReader inputStreamReader;
    private int current;

    public FileReader(File file) throws IOException {
        try {
            this.inputStreamReader = new InputStreamReader(new FileInputStream(file));
            current = inputStreamReader.read();
        } catch (FileNotFoundException e) {
            throw new ReaderException("Failed to initialize reader", e);
        }
    }

    @Override
    public char readChar() throws ReaderException {
        if (hasChars()) {
            int previous = current;
            try {
                current = inputStreamReader.read();
            } catch (IOException e) {
                throw new ReaderException("Failed to read next symbol", e);
            }
            return (char) previous;
        }
        throw new ReaderException("Failed to read next symbol");
    }

    @Override
    public boolean hasChars() {
        return current != -1;
    }

    @Override
    public void close() throws CloseException {
        try {
            inputStreamReader.close();
        } catch (IOException e) {
            throw new CloseException("Failed to close");
        }
    }
}
