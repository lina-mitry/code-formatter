package com.text.compiler.io;

import com.text.compiler.io.Reader;

public class StringReader implements Reader {
    char[] content;
    int position = -1;

    public StringReader(String content) {
        this.content = content.toCharArray();
    }

    @Override
    public boolean hasChars() {
        return content.length != position + 1;
    }

    @Override
    public char readChar() {
        return content[++position];
    }

    @Override
    public void close() {
    }
}
