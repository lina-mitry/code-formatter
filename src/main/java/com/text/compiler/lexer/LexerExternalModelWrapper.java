package com.text.compiler.lexer;

import lombok.Data;

import java.util.List;

@Data
public class LexerExternalModelWrapper {
    private List<LexerExternalModel> states;
}
