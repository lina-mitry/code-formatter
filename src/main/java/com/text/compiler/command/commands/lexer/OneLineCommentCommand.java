package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.LexerCommand;
import com.text.compiler.context.IContextLexer;

public class OneLineCommentCommand implements LexerCommand {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendLexeme(character);
        context.setTokenName("ONE_LINE_COMMENT");
    }
}
