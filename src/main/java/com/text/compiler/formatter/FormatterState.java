package com.text.compiler.formatter;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FormatterState {
    private final String state;

    public FormatterState(String state) {
        this.state = state;
    }
}
