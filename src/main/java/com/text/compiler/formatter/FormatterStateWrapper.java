package com.text.compiler.formatter;

import java.util.List;
import lombok.Data;

@Data
public class FormatterStateWrapper {
    private String state;
    private List<FormatterTransition> transitions;
}
