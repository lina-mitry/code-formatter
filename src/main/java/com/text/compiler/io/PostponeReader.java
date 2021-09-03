package com.text.compiler.io;

import com.text.compiler.context.ContextLexer;

public class PostponeReader implements Reader {
    ContextLexer ctx;
    int position = -1;

    public PostponeReader(ContextLexer ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean hasChars() {
        return ctx.getPostponeBuilder() != null && ctx.getPostponeBuilder().length() != position + 1;
    }

    @Override
    public Character readChar() {
        return ctx.getPostponeBuilder().charAt(++position);
    }

    @Override
    public void close() {
        position = -1;
        ctx.getPostponeBuilder().setLength(0);
    }
}


