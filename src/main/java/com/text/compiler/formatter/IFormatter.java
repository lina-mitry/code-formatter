package com.text.compiler.formatter;

import com.text.compiler.io.Writer;
import com.text.compiler.lexer.ILexer;

public interface IFormatter {
    String format(ILexer lexer, Writer writer) throws Exception;
}
