package com.text.compiler.command;

import com.text.compiler.token.TokenType;
import lombok.Data;

@Data
public abstract class Command {
    private final TokenType type;

    public Command(TokenType type) {
        this.type = type;
    }

    public abstract void execute(Character symbol, StringBuilder tokenBuilder);
}
