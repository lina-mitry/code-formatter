package com.text.compiler.command;

import com.text.compiler.context.Context;
import lombok.Data;

@Data
public class DefaultCommand implements Command {
    @Override
    public void execute(Character character, Context context) {
        context.appendLexeme(character);
    }
}
