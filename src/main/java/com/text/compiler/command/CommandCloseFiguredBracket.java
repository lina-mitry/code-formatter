package com.text.compiler.command;

import com.text.compiler.context.Context;

public class CommandCloseFiguredBracket implements Command {
    @Override
    public void execute(Character character, Context context) {
        context.appendLexeme(character);
        context.setTokenName("Close_Figured_Bracket");
    }
}
