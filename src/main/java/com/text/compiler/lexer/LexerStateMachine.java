package com.text.compiler.lexer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Data;

@Data
public class LexerStateMachine {
    private List<LexerStateWrapper> states;

    public Optional<LexerTransition> transition(String state, Character symbol) {
        Optional<LexerStateWrapper> stateOptional = states.stream().filter(s -> s.getState().equals(state)).findFirst();
        return stateOptional.flatMap(value -> value.getTransitions().stream()
                .filter(t -> Objects.equals(t.getSymbol(), symbol)
                        || Objects.equals(t.getSymbol(), null)).findFirst());
    }
}
