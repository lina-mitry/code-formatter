package com.text.compiler;

import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.reader.FileReader;
import com.text.compiler.validator.SimpleValidator;
import com.text.compiler.writer.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        var pathInput = Objects.requireNonNull(App.class.getClassLoader().getResource("file.txt")).getPath();
        var pathOutput = "src/main/resources/output.txt";

        var formatter = new SimpleFormatter(new SimpleValidator());

        try (var fr = new FileReader(new File(pathInput).getAbsoluteFile());
             var fw = new FileWriter(new File(pathOutput).getAbsoluteFile())) {
            log.info(formatter.format(fr, fw));
        } catch (IOException e) {
            log.error("Format failed", e);
        }
    }
}
