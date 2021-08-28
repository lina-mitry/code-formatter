package com.text.compiler.token;

import com.text.compiler.command.Command;
import lombok.Data;

import java.util.Locale;

@Data
public class Token {
    public final TokenType name;
    public final String lexeme;

    public Token(TokenType name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "TokenType: " + name + " Lexeme: " + lexeme;
    }

    public static Token of(Command command, String lexeme) {
        return new Token(command.getType(), lexeme);
    }

}
