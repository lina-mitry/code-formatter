package com.text.compiler.lexer;

import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.ValidationException;
import com.text.compiler.io.Reader;
import com.text.compiler.token.Token;
import com.text.compiler.validator.SimpleValidator;
import com.text.compiler.validator.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleLexer implements Lexer {
    private final Iterator<Token> iterator;

    public SimpleLexer(Reader reader) throws ReaderException {
        List<Token> tokenList = getTokenList(readContent(reader));
        iterator = tokenList.iterator();
    }

    @Override
    public Token nextToken() {
        return iterator.next();
    }

    @Override
    public boolean hasMoreTokens() {
        return iterator.hasNext();
    }

    private String getLexemes(String content, int indexStart) {
        int indexEnd = indexStart;
        while (indexEnd < content.length()) {
            if (Character.isLetter(content.charAt(indexEnd))) {
                indexEnd++;
            } else {
                if (indexStart == indexEnd) {
                    return String.valueOf(content.charAt(indexStart));
                }
                return content.substring(indexStart, indexEnd);
            }
        }
        return content.substring(indexStart, indexEnd);
    }

    private List<Token> getTokenList(String content) {
        Validator validator = new SimpleValidator();
        if (!validator.isValid(content)) {
            throw new ValidationException("Validation was failed");
        }
        List<Token> result = new ArrayList<>();
        int i = 0;
        while (i < content.length()) {
            if (Character.isWhitespace(content.charAt(i))) {
                i++;
            } else {
                String lexeme = getLexemes(content, i);
                i += lexeme.length();
            }
        }
        return result;
    }

    private String readContent(Reader reader) throws ReaderException {
        StringBuilder content = new StringBuilder();
        while (reader.hasChars()) {
            content.append(reader.readChar());
        }
        return content.toString();
    }

}
