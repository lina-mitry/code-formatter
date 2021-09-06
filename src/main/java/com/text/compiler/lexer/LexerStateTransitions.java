package com.text.compiler.lexer;

import java.util.HashMap;
import java.util.Map;

import com.text.compiler.lexer.LexerState;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Slf4j
public class LexerStateTransitions {
    private final Map<Pair<LexerState, Character>, LexerState> transitions = new HashMap<>();

    public LexerStateTransitions() {
        fillForStates();
        fillDelimitersStates();
        fillIfStates();
        fillTextStates();
    }

    private void fillForStates() {
        transitions.put(new Pair<>(LexerState.DEFAULT, 'f'), LexerState.FOR1);
        transitions.put(new Pair<>(LexerState.FOR1, 'o'), LexerState.FOR2);
        transitions.put(new Pair<>(LexerState.FOR2, 'r'), LexerState.FOR3);
        transitions.put(new Pair<>(LexerState.FOR3, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.FOR3, '('), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.FOR1, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.FOR1, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.FOR2, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.FOR3, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.FOR1, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.FOR2, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.FOR2, ';'), LexerState.TERMINATED);
    }

    private void fillTextStates() {
        transitions.put(new Pair<>(LexerState.DEFAULT, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.TEXT, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.TEXT, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, ';'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '('), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, ')'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '{'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '}'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, ','), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '='), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '+'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '['), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, ']'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.TEXT, '.'), LexerState.TERMINATED);
    }

    private void fillIfStates() {
        transitions.put(new Pair<>(LexerState.DEFAULT, 'i'), LexerState.IF1);
        transitions.put(new Pair<>(LexerState.IF1, 'f'), LexerState.IF2);
        transitions.put(new Pair<>(LexerState.IF2, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF2, '('), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF1, ';'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF1, ' '), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF1, '+'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF1, ','), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.IF2, null), LexerState.TEXT);
        transitions.put(new Pair<>(LexerState.IF1, null), LexerState.TEXT);
    }

    private void fillDelimitersStates() {
        transitions.put(new Pair<>(LexerState.DEFAULT, '('), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, ')'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '{'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '}'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, ';'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '+'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '\n'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '='), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, ','), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '['), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, ']'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, '.'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.DEFAULT, ' '), LexerState.SPACING);
        transitions.put(new Pair<>(LexerState.SPACING, ' '), LexerState.SPACING);
        transitions.put(new Pair<>(LexerState.SPACING, null), LexerState.TERMINATED);

        transitions.put(new Pair<>(LexerState.DEFAULT, '"'), LexerState.STRING_LITERALS);
        transitions.put(new Pair<>(LexerState.STRING_LITERALS, '"'), LexerState.TERMINATED);
        transitions.put(new Pair<>(LexerState.STRING_LITERALS, null), LexerState.STRING_LITERALS);


    }

    public LexerState nextState(LexerState lexerState, Character character) {
        //log.debug("Get next state: {} ", state.getState());
        LexerState newLexerState = transitions.get(new Pair<>(lexerState, character));
        return (newLexerState == null)
                ? transitions.get(new Pair<>(lexerState, (Character) null))
                : newLexerState;
    }

}
