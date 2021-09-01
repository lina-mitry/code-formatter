package com.text.compiler.command;

import com.text.compiler.state.State;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {
    private Map<Pair<State, Character>, Command> commandMap = new HashMap<>();

    public CommandRepository() {
        commandMap.put(new Pair<>(new State("for"), '('), new CommandFor());
        commandMap.put(new Pair<>(new State("for"), ' '), new CommandFor());
        commandMap.put(new Pair<>(new State("if"), '('), new CommandIf());
        commandMap.put(new Pair<>(new State("if"), ' '), new CommandIf());
        commandMap.put(new Pair<>(new State("default"), '('), new CommandOpenRoundBracket());
        commandMap.put(new Pair<>(new State("default"), ')'), new CommandCloseRoundBracket());
        commandMap.put(new Pair<>(new State("default"), '{'), new CommandOpenFiguredBracket());
        commandMap.put(new Pair<>(new State("default"), '}'), new CommandCloseFiguredBracket());
    }

    public Command getCommand(State state, Character ch) {
        return commandMap.getOrDefault(new Pair<>(state, ch), new DefaultCommand());
    }
}
