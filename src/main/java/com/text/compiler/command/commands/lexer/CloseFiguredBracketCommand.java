package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextLexer;

public class CloseFiguredBracketCommand implements Command<Character, IContextLexer> {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendLexeme(character);
        context.setTokenName("CLOSE_FIGURE_BRACKETS");
    }
}
