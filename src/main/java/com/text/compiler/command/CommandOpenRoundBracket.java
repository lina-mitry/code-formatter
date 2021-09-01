package com.text.compiler.command;

import com.text.compiler.context.Context;

public class CommandOpenRoundBracket implements Command {
    @Override
    public void execute(Character character, Context context) {
        context.appendLexeme(character);
        context.setTokenName("Open_Round_Bracket");
    }
}
