package com.text.compiler.formatter;

import com.text.compiler.context.ContextFormatter;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.ILexer;
import com.text.compiler.token.IToken;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Formatter implements IFormatter {
    private final ContextFormatter ctx;
    private final StringBuilder content;
    private final FormatterStateMachine stateMachine;

    public Formatter() {
        ctx = new ContextFormatter();
        content = new StringBuilder();
        stateMachine = new FormatterTransitionsLoad().getStateMachine();
    }

    @Override
    public String format(ILexer lexer, Writer writer) throws Exception {
        StateFormatter formatterState = new StateFormatter("DEFAULT");
        ctx.setContextBuilder(content);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.nextToken();
            Optional<FormatterTransition> transition = stateMachine.transition(token, formatterState.getState());
            transition.ifPresent(tr -> tr.computeCommand().execute(token, ctx));
            formatterState = new StateFormatter(transition.get().getState());
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


