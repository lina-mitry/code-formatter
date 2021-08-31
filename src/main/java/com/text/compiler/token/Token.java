package com.text.compiler.token;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Token implements IToken {
    public String name;
    public String lexeme;

    public Token(String name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "Token: " + name + " Lexeme: " + lexeme;
    }
}
