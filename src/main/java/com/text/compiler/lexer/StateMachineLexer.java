package com.text.compiler.lexer;

import com.text.compiler.command.CommandRepository;
import com.text.compiler.context.CommandContext;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.io.PostponeReader;
import com.text.compiler.io.Reader;
import com.text.compiler.state.State;
import com.text.compiler.state.StateTransitions;
import com.text.compiler.token.IToken;
import com.text.compiler.token.TokenBuilder;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;

@Slf4j
public class StateMachineLexer implements Lexer {
    private final Reader reader;
    private final CommandRepository repo;
    private final StateTransitions transitions;
    private final CommandContext ctx;
    Reader postponeReader;


    public StateMachineLexer(Reader reader) {
        this.reader = reader;
        repo = new CommandRepository();
        transitions = new StateTransitions();
        ctx = new CommandContext();
        postponeReader = new PostponeReader(ctx);
    }

    @Override
    public IToken nextToken() throws ReaderException {
        var tokenBuilder = new TokenBuilder();
        ctx.setTokenBuilder(tokenBuilder);
        State state = State.DEFAULT;
        String tokenName = StringUtils.EMPTY;
        while (postponeReader.hasChars() && state != null) {
            state = step(state, postponeReader, ctx);
            ctx.clear();
        }

        while (reader.hasChars() && state != null) {
            tokenName = state.getState();
            state = step(state, reader, ctx);
        }

        return tokenBuilder.buildToken(tokenName);
    }

    private State step(State state, Reader reader, CommandContext ctx) throws ReaderException {
        var ch = reader.readChar();
        repo.getCommand(state, ch).execute(ch, ctx);
        return transitions.nextState(state, ch);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars() || ctx.getPostponeBuilder().length() > 0;
    }

}
