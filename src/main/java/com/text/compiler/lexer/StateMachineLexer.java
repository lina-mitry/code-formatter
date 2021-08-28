package com.text.compiler.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.command.CommandRepository;
import com.text.compiler.command.lexer.DefaultCommand;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.io.Reader;
import com.text.compiler.state.State;
import com.text.compiler.state.StateMachine;
import com.text.compiler.token.Token;
import com.text.compiler.token.TokenType;


public class StateMachineLexer implements Lexer {
    private final Reader reader;
    private final CommandRepository repo;
    private final StateMachine stateMachine;

    public StateMachineLexer(Reader reader, CommandRepository repo, StateMachine stateMachine) {
        this.reader = reader;
        this.repo = repo;
        this.stateMachine = stateMachine;
    }

    @Override
    public Token nextToken() throws ReaderException {
        State state = State.Common.DEFAULT;
        StringBuilder tokenBuilder = new StringBuilder();
        Command command = new DefaultCommand(TokenType.OTHER);
        while (reader.hasChars()) {
            char ch = reader.readChar();
            if (ch == ' ') {
                break;
            }
            state = stateMachine.nextState(state, ch);
            command = repo.getCommand(state);
            command.execute(ch, tokenBuilder);
        }
        return Token.of(command, tokenBuilder.toString());
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }
}
