package com.text.compiler.formatter;

import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.token.IToken;
import com.text.compiler.token.Token;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.org.bouncycastle.util.Strings;

@Slf4j
public class OldFormatter implements Formatter {
    private static final String TABULATION = "    ";

    @Override
    public String format(Lexer lexer, Writer output) throws Exception {
        StringBuilder builder = new StringBuilder();
        StringBuilder line = new StringBuilder();
        int bracketCounter = 0;
        lexer.nextToken();
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.nextToken();
            if (token.getName().equals("CLOSE_BRACKET")) {
                line.append(Strings.lineSeparator());
            }
            line.append(token.getLexeme()).append(" ");
            if (token.getName().equals("CLOSE_BRACKET")
                    || token.getName().equals("OPEN_BRACKET")
                    || token.getName().equals("SEMICOLON")) {
                if (token.getName().equals("CLOSE_BRACKET")) {
                    builder.append(TABULATION.repeat(Math.max(--bracketCounter, 0)));
                } else {
                    builder.append(TABULATION.repeat(Math.max(bracketCounter, 0)));
                }
                if (token.getName().equals("OPEN_BRACKET")) {
                    bracketCounter++;
                }
                builder.append(line.toString().trim()).append(Strings.lineSeparator());
                line.delete(0, line.length());
            }
        }
        writeContent(builder.toString(), output);
        return builder.toString();
    }

    private void writeContent(String content, Writer writer) throws WriterException {
        char[] symbols = content.toCharArray();
        for (char symbol : symbols) {
            writer.writeChar(symbol);
        }
    }
}
