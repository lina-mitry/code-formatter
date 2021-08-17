package com.text.compiler;

import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.reader.FileReader;
import com.text.compiler.reader.StringReader;
import com.text.compiler.validator.SimpleValidator;

import java.io.File;

import com.text.compiler.writer.FileWriter;
import com.text.compiler.writer.StringWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        var pathInput = "/Users/angelinamitrij/IdeaProjects/code-formatter/src/main/resources/file.txt";
        var pathOutput = "/Users/angelinamitrij/IdeaProjects/code-formatter/src/main/resources/output.txt";
        var formatter = new SimpleFormatter(new SimpleValidator());
        String content = "if (findBookDto.getExcludedUser().isPresent()) { count = booksListRepository.countAllByQuery(findBookDto.getStatus().name(), findBookDto.getExcludedUser().get().getUserId(), findBookDto.getQuery()); page.setTotalPage((int) Math.ceil( (float) count / limit)); page.setContent(booksListRepository.findAllByQuery( findBookDto.getStatus().name(), findBookDto.getExcludedUser().get().getUserId(), findBookDto.getQuery(), limit, offset)); }";
        try (var fr = new FileReader(new File(pathInput));
             var fw = new FileWriter(new File(pathOutput));
             var sr = new StringReader(content);
             var sw = new StringWriter()) {
            log.info(formatter.format(sr, sw));
            log.info(sw.toString());
        } catch (Exception e) {
            log.error("Format failed", e);
        }
    }
}
