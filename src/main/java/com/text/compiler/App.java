package com.text.compiler;

import com.text.compiler.formatter.SimpleFormatter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        String pathInput = "file.txt";
        String pathOutput = "/output.txt";
        var formatter = new SimpleFormatter();
        try {
            formatter.format(pathInput, pathOutput);
        } catch (IOException e) {
            log.error("Failed to format", e);
        }
    }
}
