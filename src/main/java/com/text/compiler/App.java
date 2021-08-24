package com.text.compiler;

import com.text.compiler.formatter.Formatter;
import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.io.FileReader;
import com.text.compiler.io.FileWriter;
import com.text.compiler.lexer.SimpleLexer;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) {
        try (FileReader fr = new FileReader(new File(PATH_INPUT));
             FileWriter fw = new FileWriter(new File(PATH_OUTPUT))) {
            SimpleLexer lexer = new SimpleLexer(fr);
            Formatter formatter = new SimpleFormatter();
            log.info(formatter.format(lexer, fw));
        } catch (IOException e) {
            log.error("Format failed", e);
        }
    }
}
