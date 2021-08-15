package com.text.compiler.validator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleValidator implements Validator {
    @Override
    public boolean isValid(String inputPath) {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(inputPath);
             var stream = new BufferedInputStream(Objects.requireNonNull(inputStream))) {
            int symbol;
            var stack = new LinkedList<Integer>();
            while ((symbol = stream.read()) != -1) {
                if ((char) symbol == '{' || (char) symbol == '}') {
                    if ((char) symbol == '{') {
                        stack.push(symbol);
                    } else {
                        if ((char) symbol == '}' && stack.size() > 0) {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                }
            }
            return stack.size() == 0;
        } catch (IOException e) {
            log.error("Exception while validating file {}", inputPath, e);
            return false;
        }
    }
}
