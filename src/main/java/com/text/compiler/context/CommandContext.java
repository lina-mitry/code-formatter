package com.text.compiler.context;

import com.text.compiler.token.TokenBuilder;
import lombok.Data;

@Data
public class CommandContext implements Context {
    private StringBuilder postponeBuilder;
    private TokenBuilder tokenBuilder;

    public CommandContext() {
        this.postponeBuilder = new StringBuilder();
    }

    @Override
    public void appendLexeme(Character character) {
        tokenBuilder.setLexeme(character);
    }

    @Override
    public void setTokenName(String tokenName) {
        tokenBuilder.setName(tokenName);
    }

    @Override
    public void appendPostpone(Character character) {
        postponeBuilder.append(character);
    }
}
