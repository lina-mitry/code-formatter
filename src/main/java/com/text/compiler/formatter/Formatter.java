package com.text.compiler.formatter;

import com.text.compiler.reader.Reader;
import com.text.compiler.validator.Validator;
import com.text.compiler.writer.Writer;

import java.io.IOException;

public abstract class Formatter {

    Validator validator;

    public Formatter(Validator validator) {
        this.validator = validator;
    }

    public abstract String format(Reader reader, Writer writer) throws IOException;
}
