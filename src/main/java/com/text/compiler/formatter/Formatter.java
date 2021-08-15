package com.text.compiler.formatter;

import java.io.IOException;

public interface Formatter {
    void format(String inputPath, String outputPath) throws IOException;
}
