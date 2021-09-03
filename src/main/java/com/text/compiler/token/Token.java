package com.text.compiler.token;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
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
        return "TOKEN: [" + name + "] LEXEME: [" + lexeme + "]";
    }
}
