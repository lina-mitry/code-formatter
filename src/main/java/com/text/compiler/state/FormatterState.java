package com.text.compiler.state;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FormatterState {
    private final String state;

    public static final FormatterState LINE_START = new FormatterState("LINE_START");
    public static final FormatterState NEW_LINE = new FormatterState("NEW_LINE");
    public static final FormatterState TERMINATED = new FormatterState("TERMINATED");
    public static final FormatterState CODE = new FormatterState("CODE");
    public static final FormatterState LINE_END = new FormatterState("LINE_END");

    public FormatterState(String state) {
        this.state = state;
    }
}
