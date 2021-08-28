package com.text.compiler.formatter;

import com.text.compiler.io.Writer;
import com.text.compiler.lexer.Lexer;

public interface Formatter {
    String format(Lexer lexer, Writer writer) throws Exception;
}
