package com.text.compiler;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class SimpleFormatter implements Formatter {
    @Override
    public void format(String inputPath, String outputPath) {
        try (var fileInputStream = getClass().getClassLoader().getResourceAsStream(inputPath);
             var bufferedInputStream = new BufferedInputStream(Objects.requireNonNull(fileInputStream))) {
            var builder = new StringBuilder();
            var line = new StringBuilder();
            var tab = "    ";
            int symbol;
            int bracketCounter = 0;
            while ((symbol = bufferedInputStream.read()) != -1) {
                if ((char) symbol == '}') {
                    line.append(System.getProperty("line.separator"));
                }
                line.append((char) symbol);
                if ((char) symbol == '{' || (char) symbol == ';' || (char) symbol == '}') {
                    if ((char) symbol == '}') {
                        builder.append(tab.repeat(Math.max(--bracketCounter, 0)));
                    } else {
                        builder.append(tab.repeat(Math.max(bracketCounter, 0)));
                    }
                    if ((char) symbol == '{') {
                        bracketCounter++;
                    }
                    builder.append(line.toString().trim()).append(System.getProperty("line.separator"));
                    line.delete(0, line.length());
                }
            }
            writeResult(outputPath, builder.toString());
            System.out.println(builder);
        } catch (IOException e) {
            log.error("Exception while formatting file {}", inputPath, e);
        }
    }

    private void writeResult(String fileName, String content) {
        try (var stream = new FileOutputStream(Objects.requireNonNull(getClass().getResource(fileName)).getPath())) {
            stream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Exception while writing content to file {} {}", fileName, content, e);
        }
    }
}
