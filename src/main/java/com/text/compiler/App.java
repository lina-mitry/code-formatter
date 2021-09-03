package com.text.compiler;

import com.text.compiler.formatter.Formatter;
import com.text.compiler.formatter.StateMachineFormatter;
import com.text.compiler.io.FileReader;
import com.text.compiler.io.FileWriter;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.lexer.StateMachineLexer;
import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) throws Exception {
        try (var reader = new FileReader(new File(PATH_INPUT));
             var writer = new FileWriter(new File(PATH_OUTPUT))) {
            Lexer lexer = new StateMachineLexer(reader);
            Formatter formatter = new StateMachineFormatter();
            System.out.println(formatter.format(lexer, writer));
        }
    }
}
