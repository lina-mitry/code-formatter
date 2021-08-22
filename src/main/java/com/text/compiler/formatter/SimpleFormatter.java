package com.text.compiler.formatter;

import com.text.compiler.enums.Tokens;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.ValidationException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Reader;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.lexer.Token;
import com.text.compiler.validator.SimpleValidator;
import com.text.compiler.validator.Validator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.org.bouncycastle.util.Strings;

@Slf4j
public class SimpleFormatter implements Formatter {
    private static final String TABULATION = "    ";

    @Override
    public String format(Reader input, Writer output) throws IOException {
        String content = readContent(input);
        Validator validator = new SimpleValidator();
        if (!validator.isValid(content)) {
            throw new ValidationException("Validation was failed");
        }
        List<Token> tokens = Lexer.getTokenList(content);
        for (var token : tokens) {
            log.info(token.type.toString() + " " + token.content);
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = content.toCharArray();
        StringBuilder line = new StringBuilder();
        int bracketCounter = 0;
        for (char symbol : chars) {
            if (symbol == Tokens.CLOSE_BRACKET.label) {
                line.append(Strings.lineSeparator());
            }
            line.append(symbol);
            if (Arrays.stream(Tokens.values())
                    .map((token) -> token.label)
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
        StringBuilder content = new StringBuilder();
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
