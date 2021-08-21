package com.text.compiler.handlers;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.com.google.common.collect.Maps;

@Slf4j
public class TokenHandlerFactory {
    private Properties prop;

    public TokenHandlerFactory() {
        try (var stream = new FileInputStream("src/main/resources/application.properties")) {
            prop = new Properties();
            prop.load(stream);
        } catch (IOException e) {
            log.error("Failed to read properties", e);
        }
    }

    public TokenHandler getHandler(String lexeme) {
        try {
            HashMap<String, String> handlers = Maps.newHashMap(Maps.fromProperties(prop));
            if (handlers.get(lexeme) == null) {
                return new OtherTokenHandler(lexeme);
            }
            Constructor<?> ctor = Class.forName(handlers.get(lexeme)).getDeclaredConstructor(String.class);
            ctor.setAccessible(true);
            return (TokenHandler) ctor.newInstance(lexeme);

        } catch (ReflectiveOperationException e) {
            log.error("Failed to get handler", e);
        }
        return null;
    }
}
