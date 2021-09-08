package com.text.compiler.lexer;

import com.text.compiler.context.ContextLexer;
import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.PostponeReader;
import com.text.compiler.io.Reader;
import com.text.compiler.token.IToken;
import com.text.compiler.token.TokenBuilder;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lexer implements ILexer {
    private final Reader reader;
    private final ContextLexer ctx;
    private final LexerStateMachine stateMachine;

    public Lexer(Reader reader) {
        this.reader = reader;
        ctx = new ContextLexer();
        stateMachine = new LexerTransitionsLoad().getStateMachine();

    }

    @Override
    public IToken nextToken() throws ReaderException, CloseException, WriterException {
        TokenBuilder tokenBuilder = new TokenBuilder();
        ctx.setTokenBuilder(tokenBuilder);
        StateLexer lexerState = new StateLexer("DEFAULT");
        try (Reader postponeReader = new PostponeReader(ctx)) {
            while (postponeReader.hasChars() && !lexerState.getState().equals("TERMINATED")) {
                lexerState = step(lexerState, postponeReader, ctx);
            }
        }
        while (reader.hasChars() && !lexerState.getState().equals("TERMINATED")) {
            lexerState = step(lexerState, reader, ctx);
        }
        return tokenBuilder.buildToken();
    }

    private StateLexer step(StateLexer lexerState, Reader reader, ContextLexer ctx) throws ReaderException {
        Character ch = reader.readChar();
        Optional<LexerTransition> transition = stateMachine.transition(lexerState.getState(), ch);
        transition.ifPresent(tr -> tr.computeCommand().execute(ch, ctx));
        return new StateLexer(transition.get().getState());
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars() || ctx.getPostponeBuilder().length() > 0;
    }

}
