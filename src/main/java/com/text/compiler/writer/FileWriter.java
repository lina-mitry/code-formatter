package com.text.compiler.writer;

import java.io.*;

public class FileWriter implements Writer {
    private final FileOutputStream fileOutputStream;

    public FileWriter(File file) throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream(file);
    }

    @Override
    public void writeChar(char symbol) throws IOException {
        fileOutputStream.write((int) symbol);
    }

    @Override
    public void close() throws Exception {
        fileOutputStream.close();
    }
}
