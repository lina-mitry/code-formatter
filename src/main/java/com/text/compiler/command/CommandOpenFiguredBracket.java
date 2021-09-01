package com.text.compiler.command;

import com.text.compiler.context.Context;

public class CommandOpenFiguredBracket implements Command {
    @Override
    public void execute(Character character, Context context) {
        context.appendLexeme(character);
        context.setTokenName("Open_Figured_Bracket");
    }
}
