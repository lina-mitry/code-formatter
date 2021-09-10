package com.text.compiler;

import com.text.compiler.io.FileReader;
import com.text.compiler.io.FileWriter;
import com.text.compiler.io.Reader;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.ILexer;
import com.text.compiler.lexer.Lexer;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) throws Exception {
        try (Reader reader = new FileReader(new File(PATH_INPUT));
             Writer writer = new FileWriter(new File(PATH_OUTPUT))) {
            ILexer lexer = new Lexer(reader);
            //IFormatter formatter = new Formatter();
            //System.out.println(formatter.format(lexer, writer));
        }
    }
}
