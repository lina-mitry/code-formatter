package com.text.compiler.command;

import com.text.compiler.context.IContextLexer;

public interface LexerCommand {
    void execute(Character character, IContextLexer lexerContext);
}
