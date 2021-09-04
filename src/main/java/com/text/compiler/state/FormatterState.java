package com.text.compiler.state;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FormatterState {
    private final String state;

    public static final FormatterState LINE_START = new FormatterState("NEW_LINE");
    public static final FormatterState CODE = new FormatterState("CODE");
    public static final FormatterState DEFAULT = new FormatterState("DEFAULT");

    public FormatterState(String state) {
        this.state = state;
    }
}
