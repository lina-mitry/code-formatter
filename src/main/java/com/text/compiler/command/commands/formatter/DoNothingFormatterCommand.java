package com.text.compiler.command.commands.formatter;

import com.text.compiler.command.FormatterCommand;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;

public class DoNothingFormatterCommand implements FormatterCommand {
    @Override
    public void execute(IToken token, IContextFormatter context) {}
}
