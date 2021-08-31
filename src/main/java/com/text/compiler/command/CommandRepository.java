package com.text.compiler.command;

import com.text.compiler.state.State;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {
    private Map<Pair<State, Character>, Command> commandMap = new HashMap<>();

    public CommandRepository() {
        commandMap.put(new Pair<>(new State("for"), ' '), new DefaultCommand());
        commandMap.put(new Pair<>(new State("for"), '('), new PostponeCommand());
        commandMap.put(new Pair<>(new State("while"), '('), new PostponeCommand());
        commandMap.put(new Pair<>(new State("default"), '('), new DefaultCommand());
    }

    public Command getCommand(State state, Character ch) {
        return commandMap.getOrDefault(new Pair<>(state, ch), new DefaultCommand());
    }
}
