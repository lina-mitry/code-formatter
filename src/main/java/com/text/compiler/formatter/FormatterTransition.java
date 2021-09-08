package com.text.compiler.formatter;

import com.text.compiler.command.Command;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Data
@Slf4j
public class FormatterTransition {
    private String token;
    private String state;
    private String command;

    @SuppressWarnings("all")
    public Command<IToken, IContextFormatter> computeCommand() {
        Reflections reflections = new Reflections("com.text.compiler");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);
        Optional<Class<? extends Command>> commandClass = classes
                .stream()
                .filter(command -> command.getSimpleName().equals(this.command))
                .findFirst();
        try {
            Constructor<?> ctor  = commandClass.get().getDeclaredConstructor();
            ctor.setAccessible(true);
            return (Command<IToken, IContextFormatter>) ctor.newInstance();

        } catch (InstantiationException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException e) {
            log.error("Cant init command", e);
        }
        return null;
    }
}
