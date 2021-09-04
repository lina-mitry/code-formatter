package com.text.compiler.command.commands.formatter;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;

public class LineStartCloseBracketCommand implements Command<IToken, IContextFormatter> {
    @Override
    public void execute(IToken token, IContextFormatter context) {
        context.decrementIndent();
        context.writeIndent();
        context.writeLexeme(token);
        context.writeNewLine();
    }
}
