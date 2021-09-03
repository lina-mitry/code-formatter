package com.text.compiler.context;

public interface IContextLexer {
    void appendLexeme(Character character);

    void setTokenName(String tokenName);

    void appendPostpone(Character character);
}
