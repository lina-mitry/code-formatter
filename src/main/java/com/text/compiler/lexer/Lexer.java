package com.text.compiler.lexer;

import com.text.compiler.context.ContextLexer;
import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.PostponeReader;
import com.text.compiler.io.Reader;
import com.text.compiler.token.IToken;
import com.text.compiler.token.TokenBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lexer implements ILexer {
    private final Reader reader;
    private final ContextLexer ctx;
    private final LexerExternalStateTransition transition;
    private final LexerExternalCommandRepository commandRepository;

    public Lexer(Reader reader) {
        this.reader = reader;
        ctx = new ContextLexer();
        transition = new LexerExternalStateTransition("/lexerTransitions.yaml");
        commandRepository = new LexerExternalCommandRepository("/lexerTransitions.yaml");
    }

    @Override
    public IToken nextToken() throws ReaderException, CloseException, WriterException {
        TokenBuilder tokenBuilder = new TokenBuilder();
        ctx.setTokenBuilder(tokenBuilder);
        LexerState lexerState = new LexerState("DEFAULT");
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

    private LexerState step(LexerState lexerState, Reader reader, ContextLexer ctx) throws ReaderException {
        Character ch = reader.readChar();
        commandRepository.getCommand(lexerState, ch).execute(ch, ctx);
        return transition.nextState(lexerState, ch);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars() || ctx.getPostponeBuilder().length() > 0;
    }
}
