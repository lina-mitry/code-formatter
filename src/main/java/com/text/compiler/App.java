package com.text.compiler;

import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.reader.FileReader;
import com.text.compiler.validator.SimpleValidator;
import com.text.compiler.writer.FileWriter;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static final String PATH_INPUT = "src/main/resources/file.txt";
    public static final String PATH_OUTPUT = "src/main/resources/output.txt";

    public static void main(String[] args) {
        try (var fr = new FileReader(new File(PATH_INPUT));
             var fw = new FileWriter(new File(PATH_OUTPUT))) {
            var formatter = new SimpleFormatter(new SimpleValidator());
            log.info(formatter.format(fr, fw));
        } catch (IOException e) {
            log.error("Format failed", e);
        }
    }
}
