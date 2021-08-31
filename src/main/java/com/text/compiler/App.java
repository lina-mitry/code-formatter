package com.text.compiler;

import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Reader;
import com.text.compiler.io.StringReader;
import com.text.compiler.lexer.StateMachineLexer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) throws ReaderException {
        Reader reader = new StringReader("for(");
        var lexer = new StateMachineLexer(reader);
        while (lexer.hasMoreTokens()) {
            System.out.println(lexer.nextToken());
        }
    }
}
