package com.text.compiler.lexer;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LexerState {
    private final String state;

    public static final LexerState DEFAULT = new LexerState("DEFAULT");
    public static final LexerState TERMINATED = new LexerState("TERMINATED");
    public static final LexerState OPEN_FIGURE_BRACKETS = new LexerState("OPEN_FIGURE_BRACKETS");
    public static final LexerState CLOSE_FIGURE_BRACKETS = new LexerState("CLOSE_FIGURE_BRACKETS");
    public static final LexerState OPEN_ROUND_BRACKETS = new LexerState("OPEN_ROUND_BRACKETS");
    public static final LexerState CLOSE_ROUND_BRACKETS = new LexerState("CLOSE_ROUND_BRACKETS");
    public static final LexerState OPEN_SQUARE_BRACKETS = new LexerState("OPEN_SQUARE_BRACKETS");
    public static final LexerState CLOSE_SQUARE_BRACKETS = new LexerState("CLOSE_SQUARE_BRACKETS");
    public static final LexerState SEMICOLON = new LexerState("SEMICOLON");
    public static final LexerState WHITESPACE = new LexerState("WHITESPACE");
    public static final LexerState SPACING = new LexerState("SPACING");
    public static final LexerState CHAR = new LexerState("CHAR");
    public static final LexerState TEXT = new LexerState("TEXT");
    public static final LexerState FOR1 = new LexerState("FOR1");
    public static final LexerState FOR2 = new LexerState("FOR2");
    public static final LexerState FOR3 = new LexerState("FOR3");
    public static final LexerState IF1 = new LexerState("IF1");
    public static final LexerState IF2 = new LexerState("IF2");
    public static final LexerState NEW_LINE = new LexerState("NEW_LINE");
    public static final LexerState COMMA = new LexerState("COMMA");
    public static final LexerState EQUAL_SIGN = new LexerState("EQUAL_SIGN");
    public static final LexerState PLUS_SIGN = new LexerState("PLUS_SIGN");
    public static final LexerState DOT = new LexerState("DOT");
    public static final LexerState STRING_LITERALS = new LexerState("STRING_LITERALS");

    public LexerState(String state) {
        this.state = state;
    }
}
