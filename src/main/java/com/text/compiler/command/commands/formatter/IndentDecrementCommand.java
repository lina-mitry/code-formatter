package com.text.compiler.command.commands.formatter;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;

public class IndentDecrementCommand implements Command<IToken, IContextFormatter> {
    @Override
    public void execute(IToken token, IContextFormatter context) {

        context.decrementIntent();
        context.writeIndent();
        context.writeNewLine();
        context.writeLexeme(token);
    }
}
