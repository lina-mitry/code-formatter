package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextLexer;
import com.text.compiler.lexer.LexerState;

public class IfCommand implements Command<Character, IContextLexer> {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendLexeme(character);
        context.setTokenName(LexerState.IF2.getState());
    }
}
