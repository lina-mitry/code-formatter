package com.text.compiler.lexer;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Slf4j
public class LexerTransitionsLoad {
    private final LexerStateMachine stateMachine;

    public LexerTransitionsLoad() {
        Constructor constructor = new Constructor(LexerStateMachine.class);
        Yaml yaml = new Yaml(constructor);
        stateMachine =
                yaml.load(this.getClass().getResourceAsStream("/lexerTransitions.yaml"));
    }

    public LexerStateMachine getStateMachine() {
        return stateMachine;
    }
}
