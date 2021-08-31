package com.text.compiler.state;

import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class StateTransitions {
    private static final Map<Pair<State, Character>, State> transitions = new HashMap<>();

    public StateTransitions() {
        fillForStates();
        fillIfStates();
        fillWhileStates();
    }

    private static void fillForStates() {
        transitions.put(new Pair<>(new State("default"), 'f'), new State("f"));
        transitions.put(new Pair<>(new State("f"), 'o'), new State("fo"));
        transitions.put(new Pair<>(new State("fo"), 'r'), new State("for"));
        transitions.put(new Pair<>(new State("for"), '('), null);
        transitions.put(new Pair<>(new State("default"), '('), null);
    }

    private static void fillIfStates() {
        transitions.put(new Pair<>(new State("default"), 'i'), new State("f"));
        transitions.put(new Pair<>(new State("i"), 'f'), new State("if"));
    }

    private static void fillWhileStates() {
        transitions.put(new Pair<>(new State("default"), 'w'), new State("w"));
        transitions.put(new Pair<>(new State("w"), 'h'), new State("wh"));
        transitions.put(new Pair<>(new State("wh"), 'i'), new State("whi"));
        transitions.put(new Pair<>(new State("whi"), 'l'), new State("whil"));
        transitions.put(new Pair<>(new State("whil"), 'e'), new State("while"));
        transitions.put(new Pair<>(new State("while"), '('), null);
    }

    public State nextState(State state, Character character) {
        return transitions
                .getOrDefault(new Pair<>(state, character),
                        new State("Unknown"));
    }

}
