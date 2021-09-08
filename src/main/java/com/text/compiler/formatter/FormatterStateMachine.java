package com.text.compiler.formatter;

import com.text.compiler.token.IToken;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Data;

@Data
public class FormatterStateMachine {
    private List<FormatterStateWrapper> states;

    public Optional<FormatterTransition> transition(IToken token, String state) {
        Optional<FormatterStateWrapper> stateOptional = states.stream()
                .filter(s -> s.getState().equals(state)).findFirst();

        return stateOptional.flatMap(value -> value.getTransitions().stream()
                .filter(transition -> Objects.equals(transition.getToken(), token.getName())
                        || Objects.equals(transition.getToken(), null)).findFirst());
    }

}
