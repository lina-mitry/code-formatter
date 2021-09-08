package com.text.compiler.command.commands.formatter;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;

public class OpenFigureBracketFormatterCommand implements Command<IToken, IContextFormatter> {
    @Override
    public void execute(IToken token, IContextFormatter context) {
        context.writeLexeme(token);
        context.writeNewLine();
        context.incrementIndent();
    }
}