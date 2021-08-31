package com.text.compiler.token;

public class TokenBuilder {
    private String token;
    private StringBuilder lexeme;

    public TokenBuilder() {
        lexeme = new StringBuilder();
    }

    public void setName(String token) {
        this.token = token;
    }

    public void setLexeme(Character ch) {
        lexeme.append(ch);
    }

    public Token buildToken(String tokenName) {
        return new Token(tokenName, lexeme.toString());
    }
}
