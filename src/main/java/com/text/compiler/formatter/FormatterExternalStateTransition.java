package com.text.compiler.formatter;

import com.text.compiler.token.IToken;
import org.javatuples.Pair;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FormatterExternalStateTransition {
    private final Map<Pair<FormatterState, String>, FormatterState> transition;

    public FormatterExternalStateTransition(String file) {
        transition = new HashMap<>();
        initialTransitions(file);
    }

    private void initialTransitions(String file) {
        InputStream inputStream = Formatter.class.getResourceAsStream(file);
        Constructor constructor = new Constructor(FormatterExternalModelWrapper.class);
        TypeDescription typeDescription = new TypeDescription(FormatterExternalModelWrapper.class);
        typeDescription.addPropertyParameters("states", FormatterExternalModel.class);
        constructor.addTypeDescription(typeDescription);
        Yaml yaml = new Yaml(constructor);
        FormatterExternalModelWrapper type = yaml.load(inputStream);

        type.getStates().forEach(model -> {
            model.getActions().forEach(action -> {
                transition.put(new Pair<>(new FormatterState(model.getState()), action.getToken()), new FormatterState(action.getTransition()));
            });
        });
    }

    public FormatterState nextState(FormatterState formatterState, IToken token) {

        FormatterState newFormatterState = transition.get(new Pair<>(formatterState, token.getName()));
        return (newFormatterState == null)
                ? transition.get(new Pair<>(formatterState, (String) null))
                : newFormatterState;
    }

}
