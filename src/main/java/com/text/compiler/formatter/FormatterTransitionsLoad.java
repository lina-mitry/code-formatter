package com.text.compiler.formatter;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class FormatterTransitionsLoad {
    private final FormatterStateMachine stateMachine;

    public FormatterTransitionsLoad() {
        Constructor constructor = new Constructor(FormatterStateMachine.class);
        Yaml yaml = new Yaml(constructor);
        stateMachine =
                yaml.load(this.getClass().getResourceAsStream("/formatterTransitions.yaml"));
    }

    public FormatterStateMachine getStateMachine() {
        return stateMachine;
    }
}
