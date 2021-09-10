package com.text.compiler.lexer;

import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LexerExternalStateTransition {
    private final Map<Pair<LexerState, Character>, LexerState> transition;

    public LexerExternalStateTransition(String file) {
        transition = new HashMap<>();
        initialTransitions(file);
    }

    private void initialTransitions(String file) {
        InputStream inputStream = Lexer.class.getResourceAsStream(file);
        Constructor constructor = new Constructor(LexerExternalModelWrapper.class);
        TypeDescription typeDescription = new TypeDescription(LexerExternalModelWrapper.class);
        typeDescription.addPropertyParameters("states", LexerExternalModel.class);
        constructor.addTypeDescription(typeDescription);
        Yaml yaml = new Yaml(constructor);
        LexerExternalModelWrapper type = yaml.load(inputStream);

        type.getStates().forEach(model -> {
            model.getActions().forEach(action -> {
                transition.put(new Pair<>(new LexerState(model.getState()), action.getInput()), new LexerState(action.getTransition()));
            });
        });
    }

    public LexerState nextState(LexerState lexerState, Character character) {
        LexerState newLexerState = transition.get(new Pair<>(lexerState, character));
        return (newLexerState == null)
                ? transition.get(new Pair<>(lexerState, (Character) null))
                : newLexerState;
    }
}
