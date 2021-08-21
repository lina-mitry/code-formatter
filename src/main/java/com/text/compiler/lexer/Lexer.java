package com.text.compiler.lexer;

import com.text.compiler.handlers.TokenHandlerFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Lexer {

    public static String getLexemes(String content, int i) {
        int j = i;
        while (j < content.length()) {
            if (Character.isLetter(content.charAt(j))) {
                j++;
            } else {
                if (i == j) {
                    return String.valueOf(content.charAt(i));
                }
                return content.substring(i, j);
            }
        }
        return content.substring(i, j);
    }

    public static List<Token> getTokenList(String input) {
        var factory = new TokenHandlerFactory();
        List<Token> result = new ArrayList<>();
        for (int i = 0; i < input.length();) {
            if (Character.isWhitespace(input.charAt(i))) {
                i++;
            } else {
                String lexemes = getLexemes(input, i);
                i += lexemes.length();
                result.add(factory.getHandler(lexemes).handle());
            }
        }
        return result;
    }
}
