package com.text.compiler.writer;

import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;

public class StringWriter implements Writer {
    private final StringBuilder content;

    public StringWriter() {
        this.content = new StringBuilder(StringUtils.EMPTY);
    }

    @Override
    public void writeChar(char symbol) {
        content.append(symbol);
    }

    @Override
    public String toString() {
        return content.toString();
    }

    @Override
    public void close() {
    }
}
