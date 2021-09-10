package com.text.compiler.lexer;

import com.text.compiler.command.LexerCommand;
import com.text.compiler.exceptions.CreateCommandException;
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
public class LexerExternalCommandRepository {
    private final Map<Pair<LexerState, Character>, LexerCommand> commandMap;
    private static final String COMMAND_PACKAGE = "com.text.compiler.command.commands.lexer.";

    public LexerExternalCommandRepository(String file) {
        commandMap = new HashMap<>();
        initialCommandRepository(file);
    }

    private void initialCommandRepository(String file) {
        try (InputStream inputStream = Lexer.class.getResourceAsStream(file);) {

            Constructor constructor = new Constructor(LexerExternalModelWrapper.class);
            TypeDescription typeDescription = new TypeDescription(LexerExternalModelWrapper.class);
            typeDescription.addPropertyParameters("states", LexerExternalModel.class);
            constructor.addTypeDescription(typeDescription);
            Yaml yaml = new Yaml(constructor);
            LexerExternalModelWrapper type = yaml.load(inputStream);

            type.getStates().forEach(model -> {
                model.getActions().forEach(action -> {
                    commandMap.put(new Pair<>(new LexerState(model.getState()), action.getInput()), createCommand(action.getCommand()));
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LexerCommand createCommand(String commandName) {
        String fullCommandName = COMMAND_PACKAGE + commandName;
        try {
            return (LexerCommand) Class.forName(fullCommandName)
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

    public LexerCommand getCommand(LexerState lexerState, Character ch) {
        LexerCommand command = commandMap.get(new Pair<>(lexerState, ch));
        return (command == null)
                ? commandMap.get(new Pair<>(lexerState, (Character) null))
                : command;
    }
}
