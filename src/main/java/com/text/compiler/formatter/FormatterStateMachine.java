package com.text.compiler.formatter;

import com.text.compiler.token.IToken;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Data;

@Data
public class FormatterStateMachine {
    private List<FormatterStateWrapper> states;

    public Optional<FormatterTransition> transition(IToken symbol, String state) {
        Optional<FormatterStateWrapper> stateOptional = states.stream()
                .filter(s -> s.getState().equals(state)).findFirst();

        return stateOptional.flatMap(value -> value.getTransitions().stream()
                .filter(t -> Objects.equals(t.getSymbol(), symbol.getName())
                        || Objects.equals(t.getSymbol(), null)).findFirst());
    }

}
