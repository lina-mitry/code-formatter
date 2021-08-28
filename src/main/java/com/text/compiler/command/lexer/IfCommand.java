package com.text.compiler.command.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.token.TokenType;

public class IfCommand extends Command {
    public IfCommand(TokenType type) {
        super(type);
    }

    @Override
    public void execute(Character symbol, StringBuilder tokenBuilder) {
        tokenBuilder.append(symbol);
    }
}
