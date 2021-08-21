package com.text.compiler;

import static com.text.compiler.lexer.Lexer.getTokenList;

import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.io.FileReader;
import com.text.compiler.io.FileWriter;
import com.text.compiler.lexer.Token;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) {
        try (var fr = new FileReader(new File(PATH_INPUT));
             var fw = new FileWriter(new File(PATH_OUTPUT))) {
            var formatter = new SimpleFormatter();
            log.info(formatter.format(fr, fw));
        } catch (IOException e) {
            log.error("Format failed", e);
        }

        var string = "if{if{var;}}";
        List<Token> tokens = getTokenList(string);
        for (Token t : tokens) {
            System.out.println(t);
        }

    }
}
