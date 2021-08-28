package com.text.compiler.command.lexer;

import com.text.compiler.command.Command;
import com.text.compiler.token.TokenType;

public class DefaultCommand extends Command {
    public DefaultCommand(TokenType type) {
        super(type);
    }

    @Override
    public void execute(Character symbol, StringBuilder tokenBuilder) {
        tokenBuilder.append(symbol);
    }
}
