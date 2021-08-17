package com.text.compiler.reader;

public class StringReader implements Reader {
    char[] content;
    int position = 0;

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
