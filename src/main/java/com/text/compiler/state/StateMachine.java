package com.text.compiler.state;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private static final Map<Map<State, Character>, State> transitions = new HashMap<>();

    public StateMachine() {
        fillForStates();
        fillIfStates();
    }

    private static void fillForStates() {
        transitions.put(Collections.singletonMap(State.Common.DEFAULT, 'f'), State.For.F);
        transitions.put(Collections.singletonMap(State.For.F, 'o'), State.For.FO);
        transitions.put(Collections.singletonMap(State.For.FO, 'r'), State.For.TERMINATE);
    }

    private static void fillIfStates() {
        transitions.put(Collections.singletonMap(State.Common.DEFAULT, 'i'), State.If.I);
        transitions.put(Collections.singletonMap(State.If.I, 'f'), State.If.TERMINATE);
    }

    public State nextState(State state, Character character) {
        return transitions
                .getOrDefault(Collections.singletonMap(state, Character.toLowerCase(character)),
                        State.Common.UNKNOWN);
    }

}
