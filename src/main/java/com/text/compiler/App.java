package com.text.compiler;

import com.text.compiler.command.CommandRepository;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.io.StringReader;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.lexer.StateMachineLexer;
import com.text.compiler.state.StateMachine;
import com.text.compiler.token.Token;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) {
//        try (FileReader fr = new FileReader(new File(PATH_INPUT));
//             FileWriter fw = new FileWriter(new File(PATH_OUTPUT))) {
//            StateMachineLexer lexer = new StateMachineLexer(fr);
//            Formatter formatter = new SimpleFormatter();
//            formatter.format(lexer, fw);
//        } catch (IOException e) {
//            log.error("Format failed", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        List<Token> tokens = new ArrayList<>();
        try (var reader = new StringReader("for fo if")) {
            Lexer lexer = new StateMachineLexer(reader, new CommandRepository(), new StateMachine());
            while (lexer.hasMoreTokens()) {
                tokens.add(lexer.nextToken());
            }
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
        }
        tokens.forEach(System.out::println);
    }
}
