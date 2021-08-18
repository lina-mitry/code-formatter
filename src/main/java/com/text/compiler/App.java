package com.text.compiler;

import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.reader.FileReader;
import com.text.compiler.validator.SimpleValidator;

import java.io.File;

import com.text.compiler.writer.FileWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        var pathInput = "file.txt";
        var pathOutput = "output.txt";
        var formatter = new SimpleFormatter(new SimpleValidator());
        try (var fr = new FileReader(new File(pathInput));
             var fw = new FileWriter(new File(pathOutput))) {
            log.info(formatter.format(fr, fw));
        } catch (Exception e) {
            log.error("Format failed", e);
        }
    }
}
