package com.text.compiler.context;

import com.text.compiler.token.IToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ContextFormatter implements IContextFormatter {
    private StringBuilder contextBuilder;
    private Integer indent = 0;
    private static final String TAB = "    ";

    @Override
    public void writeLexeme(IToken token) {
        contextBuilder.append(token.getLexeme());
    }

    @Override
    public void writeNewLine() {
        contextBuilder.append('\n');
    }

    @Override
    public void writeIndent() {
        contextBuilder.append(TAB.repeat(indent));
    }

    @Override
    public void incrementIndent() {
        indent++;
    }

    @Override
    public void decrementIndent() {
        indent--;
    }
}
