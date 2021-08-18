package com.text.compiler.writer;

import com.text.compiler.exceptions.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter implements Writer {
    private final FileOutputStream fileOutputStream;

    public FileWriter(File file) throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream(file);
    }

    @Override
    public void writeChar(char symbol) throws WriterException {
        try {
            fileOutputStream.write((int) symbol);
        } catch (IOException e) {
            throw new WriterException("Writer exception");
        }
    }

    @Override
    public void close() throws WriterException {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new WriterException("Writer exception");
        }
    }
}
