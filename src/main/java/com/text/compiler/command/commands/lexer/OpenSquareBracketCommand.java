package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextLexer;
import com.text.compiler.state.LexerState;

public class OpenSquareBracketCommand implements Command<Character, IContextLexer> {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendLexeme(character);
        context.setTokenName(LexerState.OPEN_SQUARE_BRACKETS.getState());
    }
}
