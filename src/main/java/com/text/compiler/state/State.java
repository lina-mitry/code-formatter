package com.text.compiler.state;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class State {
    private final String state;

    public static final State DEFAULT = new State("default");

    public State(String state) {
        this.state = state;
    }
}
