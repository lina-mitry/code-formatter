package com.text.compiler.command.commands.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextLexer;
import lombok.Data;

@Data
public class PostponeCommand implements Command<Character, IContextLexer> {
    @Override
    public void execute(Character character, IContextLexer context) {
        context.appendPostpone(character);
    }
}
