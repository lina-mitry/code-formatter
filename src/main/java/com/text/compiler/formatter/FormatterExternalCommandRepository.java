package com.text.compiler.formatter;

import com.text.compiler.command.FormatterCommand;
import com.text.compiler.exceptions.CreateCommandException;
import com.text.compiler.token.IToken;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FormatterExternalCommandRepository {
    private final Map<Pair<FormatterState, String>, FormatterCommand> commandMap;
    private static final String COMMAND_PACKAGE = "com.text.compiler.command.commands.formatter.";

    public FormatterExternalCommandRepository(String file) {
        commandMap = new HashMap<>();
        initialCommandRepository(file);
    }

    private void initialCommandRepository(String file) {
        try (
                InputStream inputStream = Formatter.class.getResourceAsStream(file);
        ) {
            Constructor constructor = new Constructor(FormatterExternalModelWrapper.class);
            TypeDescription typeDescription = new TypeDescription(FormatterExternalModelWrapper.class);
            typeDescription.addPropertyParameters("states", FormatterExternalModel.class);
            constructor.addTypeDescription(typeDescription);
            Yaml yaml = new Yaml(constructor);
            FormatterExternalModelWrapper type = yaml.load(inputStream);

            type.getStates().forEach(model -> {
                model.getActions().forEach(action -> {
                    commandMap.put(new Pair<>(new FormatterState(model.getState()), action.getToken()), createCommand(action.getCommand()));
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FormatterCommand createCommand(String commandName) {
        String fullCommandName = COMMAND_PACKAGE + commandName;
        try {
            return (FormatterCommand) Class.forName(fullCommandName)
                    .getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException e) {
            log.error("Unable to create command with name {}", fullCommandName);
            throw new CreateCommandException("Unable to create command", e);
        }
    }

    public FormatterCommand getCommand(FormatterState formatterState, IToken token) {
        FormatterCommand command = commandMap.get(new Pair<>(formatterState, token.getName()));
        return (command == null)
                ? commandMap.get(new Pair<>(formatterState, (String) null))
                : command;
    }
}
