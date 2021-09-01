package com.text.compiler.command;

import com.text.compiler.context.Context;

public class CommandFor implements Command {
    @Override
    public void execute(Character character, Context context) {
        context.appendPostpone(character);
        context.setTokenName("for");
    }
}
