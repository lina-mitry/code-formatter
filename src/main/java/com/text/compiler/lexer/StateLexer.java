package com.text.compiler.lexer;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class StateLexer {
    private final String state;

    public StateLexer(String state) {
        this.state = state;
    }
}
