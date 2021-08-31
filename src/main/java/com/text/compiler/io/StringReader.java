package com.text.compiler.io;

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
    public Character readChar() {
        return content[++position];
    }

    @Override
    public void close() {
    }
}
