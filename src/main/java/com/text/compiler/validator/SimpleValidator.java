package com.text.compiler.validator;

import com.text.compiler.enums.Tokens;
import com.text.compiler.exceptions.ValidationException;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleValidator implements Validator {
    @Override
    public void validate(String content) throws ValidationException {
        checkBrackets(content);
    }

    private void checkBrackets(String content) throws ValidationException {
        var chars = content.toCharArray();
        var stack = new LinkedList<Character>();
        for (char symbol : chars) {
            if (symbol == Tokens.OPEN_BRACKET.label
                    || symbol == Tokens.CLOSE_BRACKET.label) {
                if (symbol == Tokens.OPEN_BRACKET.label) {
                    stack.push(symbol);
                } else {
                    if (stack.size() > 0) {
                        stack.pop();
                    } else {
                        throw new ValidationException("Failed to check brackets");
                    }
                }
            }
        }
        if (stack.size() != 0) {
            throw new ValidationException("Failed to check brackets");
        }
    }
}
