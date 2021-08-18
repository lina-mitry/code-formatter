package com.text.compiler.formatter;

import com.text.compiler.enums.Tokens;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.reader.Reader;
import com.text.compiler.validator.Validator;
import com.text.compiler.writer.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.org.bouncycastle.util.Strings;

@Slf4j
public class SimpleFormatter extends Formatter {
    private static final String TABULATION = "    ";

    public SimpleFormatter(Validator validator) {
        super(validator);
    }

    @Override
    public String format(Reader input, Writer output) throws IOException {
        var content = readContent(input);
        validator.validate(content);
        var builder = new StringBuilder();
        var chars = content.toCharArray();
        var line = new StringBuilder();
        var bracketCounter = 0;
        for (char symbol : chars) {
            if (symbol == Tokens.CLOSE_BRACKET.label) {
                line.append(Strings.lineSeparator());
            }
            line.append(symbol);
            if (Arrays.stream(Tokens.values())
                    .map((it) -> it.label)
                    .collect(Collectors.toList())
                    .contains(symbol)) {
                if (symbol == Tokens.CLOSE_BRACKET.label) {
                    builder.append(TABULATION.repeat(Math.max(--bracketCounter, 0)));
                } else {
                    builder.append(TABULATION.repeat(Math.max(bracketCounter, 0)));
                }
                if (symbol == Tokens.OPEN_BRACKET.label) {
                    bracketCounter++;
                }
                builder.append(line.toString().trim()).append(Strings.lineSeparator());
                line.delete(0, line.length());
            }
        }
        writeContent(builder.toString(), output);
        return builder.toString();
    }

    private String readContent(Reader reader) throws ReaderException {
        var content = new StringBuilder();
        while (reader.hasChars()) {
            content.append(reader.readChar());
        }
        return content.toString();
    }

    private void writeContent(String content, Writer writer) throws WriterException {
        char[] symbols = content.toCharArray();
        for (char symbol : symbols) {
            writer.writeChar(symbol);
        }
    }
}
