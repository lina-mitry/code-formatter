package com.text.compiler.formatter;

import com.text.compiler.context.ContextFormatter;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.ILexer;
import com.text.compiler.token.IToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Formatter implements IFormatter {
    private final ContextFormatter ctx;
    private final StringBuilder content;
    private final FormatterExternalStateTransition transition;
    private final FormatterExternalCommandRepository commandRepository;

    public Formatter() {
        ctx = new ContextFormatter();
        content = new StringBuilder();
        transition = new FormatterExternalStateTransition("/formatterTransitions.yaml");
        commandRepository = new FormatterExternalCommandRepository("formatterTransitions.yaml");
    }

    @Override
    public String format(ILexer lexer, Writer writer) throws Exception {
        FormatterState formatterState = new FormatterState("DEFAULT");
        ctx.setContextBuilder(content);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.nextToken();
            commandRepository.getCommand(formatterState, token).execute(token, ctx);
            formatterState = transition.nextState(formatterState, token);
        }
        writeContent(content.toString(), writer);
        return content.toString();
    }

    private void writeContent(String content, Writer writer) throws WriterException {
        char[] symbols = content.toCharArray();
        for (char symbol : symbols) {
            writer.writeChar(symbol);
        }
    }
}


