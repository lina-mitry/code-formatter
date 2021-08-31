package com.text.compiler.command;

import com.text.compiler.context.Context;

public interface Command {
    void execute(Character character, Context context);
}
