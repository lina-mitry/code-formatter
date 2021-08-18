package com.text.compiler.writer;

import com.text.compiler.exceptions.WriterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriter implements Writer {
    private final FileOutputStream fileOutputStream;
    private final File content;

    @SuppressWarnings("All")
    public FileWriter(File file) throws WriterException {
        try {
            this.content = file;
            file.createNewFile();
            this.fileOutputStream = new FileOutputStream(file);
        } catch (IOException e) {
            throw new WriterException("failed to initialize writer", e);
        }
    }

    @Override
    public void writeChar(char symbol) throws WriterException {
        try {
            fileOutputStream.write(symbol);
        } catch (IOException e) {
            throw new WriterException("Fail to write file " + content.getName(), e);
        }
    }

    @Override
    public void close() throws WriterException {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new WriterException("Fail to close file " + content.getName(), e);
        }
    }
}
