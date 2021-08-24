package com.text.compiler.formatter;

import com.text.compiler.io.Writer;
import com.text.compiler.lexer.SimpleLexer;
import java.io.IOException;

public interface Formatter {
    String format(SimpleLexer lexer, Writer writer) throws IOException;
}
