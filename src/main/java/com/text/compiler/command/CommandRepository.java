package com.text.compiler.command;

import com.text.compiler.command.lexer.DefaultCommand;
import com.text.compiler.command.lexer.ForCommand;
import com.text.compiler.command.lexer.IfCommand;
import com.text.compiler.state.State;
import com.text.compiler.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository {
    private static final Map<State, Command> stateCommandMap = new HashMap<>();

    public CommandRepository() {
        stateCommandMap.put(State.For.TERMINATE, new ForCommand(TokenType.FOR));
        stateCommandMap.put(State.If.TERMINATE, new IfCommand(TokenType.IF));
    }

    public Command getCommand(State state) {
        return stateCommandMap.getOrDefault(state, new DefaultCommand(TokenType.OTHER));
    }
}
