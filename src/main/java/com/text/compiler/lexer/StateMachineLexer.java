package com.text.compiler.lexer;

import com.text.compiler.command.LexerCommandRepository;
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
public class StateMachineLexer implements Lexer {
    private final Reader reader;
    private final LexerCommandRepository repo;
    private final LexerStateTransitions transitions;
    private final ContextLexer ctx;

    public StateMachineLexer(Reader reader) {
        this.reader = reader;
        repo = new LexerCommandRepository();
        transitions = new LexerStateTransitions();
        ctx = new ContextLexer();
    }

    @Override
    public IToken nextToken() throws ReaderException, CloseException, WriterException {
        TokenBuilder tokenBuilder = new TokenBuilder();
        ctx.setTokenBuilder(tokenBuilder);
        LexerState lexerState = LexerState.DEFAULT;
        try (Reader postponeReader = new PostponeReader(ctx)) {
            while (postponeReader.hasChars() && lexerState != LexerState.TERMINATED) {
                lexerState = step(lexerState, postponeReader, ctx);
            }
        }

        while (reader.hasChars() && lexerState != LexerState.TERMINATED) {
            lexerState = step(lexerState, reader, ctx);
        }
        return tokenBuilder.buildToken();
    }

    private LexerState step(LexerState lexerState, Reader reader, ContextLexer ctx) throws ReaderException {
        Character ch = reader.readChar();
        repo.getCommand(lexerState, ch).execute(ch, ctx);
        return transitions.nextState(lexerState, ch);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars() || ctx.getPostponeBuilder().length() > 0;
    }

}
