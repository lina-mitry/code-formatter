package com.text.compiler.formatter;

import com.text.compiler.io.Reader;
import com.text.compiler.io.Writer;
import java.io.IOException;

public interface Formatter {

    String format(Reader reader, Writer writer) throws IOException;
}
