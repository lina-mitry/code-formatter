package com.text.compiler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {

        String pathInput = "file.txt";
        String pathOutput = "/output.txt";
        var validator = new SimpleValidator();
        var formatter = new SimpleFormatter();
        if (validator.isValid(pathInput)) {
            formatter.format(pathInput, pathOutput);
        } else {
            log.error("File invalid");
        }
    }
}
