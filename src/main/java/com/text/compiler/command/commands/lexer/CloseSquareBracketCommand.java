package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextLexer;
import com.text.compiler.state.LexerState;

public class CloseSquareBracketCommand implements Command<Character, IContextLexer> {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendLexeme(character);
        context.setTokenName(LexerState.CLOSE_SQUARE_BRACKETS.getState());
    }
}
