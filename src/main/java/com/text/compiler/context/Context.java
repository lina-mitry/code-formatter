package com.text.compiler.context;

public interface Context {
    void appendLexeme(Character character);

    void setTokenName(String tokenName);

    void appendPostpone(Character character);

    void clear();
}
