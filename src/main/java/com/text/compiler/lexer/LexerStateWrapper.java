package com.text.compiler.lexer;

import java.util.List;
import lombok.Data;

@Data
public class LexerStateWrapper {
    private String state;
    private List<LexerTransition> transitions;
}
