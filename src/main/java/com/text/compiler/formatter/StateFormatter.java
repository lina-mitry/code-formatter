package com.text.compiler.formatter;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class StateFormatter {
    private final String state;

    public StateFormatter(String state) {
        this.state = state;
    }
}
