package com.text.compiler.state;

import com.text.compiler.token.IToken;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class FormatterStateTransitions {
    private final Map<Pair<FormatterState, String>, FormatterState> transitions = new HashMap<>();

    public FormatterStateTransitions() {
        fillStates();
    }

    private void fillStates() {
        transitions.put(new Pair<>(FormatterState.LINE_START, null), FormatterState.CODE);
        transitions.put(new Pair<>(FormatterState.CODE, null), FormatterState.CODE);
        transitions.put(new Pair<>(FormatterState.CODE, LexerState.OPEN_FIGURE_BRACKETS.getState()), FormatterState.LINE_START);
        transitions.put(new Pair<>(FormatterState.CODE, LexerState.CLOSE_FIGURE_BRACKETS.getState()), FormatterState.LINE_START);
        transitions.put(new Pair<>(FormatterState.CODE, LexerState.SEMICOLON.getState()), FormatterState.LINE_START);
        transitions.put(new Pair<>(FormatterState.LINE_START, LexerState.SPACING.getState()), FormatterState.CODE);
        transitions.put(new Pair<>(FormatterState.CODE, LexerState.NEW_LINE.getState()), FormatterState.CODE);
    }

    public FormatterState nextState(FormatterState formatterState, IToken token) {

        FormatterState newFormatterState = transitions.get(new Pair<>(formatterState, token.getName()));
        return (newFormatterState == null)
                ? transitions.get(new Pair<>(formatterState, (String) null))
                : newFormatterState;
    }
}
