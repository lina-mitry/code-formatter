package com.text.compiler.token;

public class Token implements IToken {
    public final String name;
    public final String lexeme;

    public Token(String name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    public String toString() {
        return name + ": " + lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }
}
