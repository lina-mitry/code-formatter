package com.text.compiler.formatter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FormatterExternalModel {
    private String state;
    private List<FormatterAction> actions;
}
