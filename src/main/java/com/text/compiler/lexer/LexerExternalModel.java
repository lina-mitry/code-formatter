package com.text.compiler.lexer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LexerExternalModel {
    private String state;
    private List<LexerAction> actions;
}
