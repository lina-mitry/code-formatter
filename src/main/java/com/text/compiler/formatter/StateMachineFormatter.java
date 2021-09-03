package com.text.compiler.formatter;

import com.text.compiler.command.FormatterCommandRepository;
import com.text.compiler.context.ContextFormatter;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.state.FormatterState;
import com.text.compiler.state.FormatterStateTransitions;
import com.text.compiler.token.IToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateMachineFormatter implements Formatter {
    private final FormatterCommandRepository repo;
    private final ContextFormatter ctx;
    private final FormatterStateTransitions transitions;
    private final StringBuilder content;

    public StateMachineFormatter() {
        repo = new FormatterCommandRepository();
        ctx = new ContextFormatter();
        transitions = new FormatterStateTransitions();
        content = new StringBuilder();
    }

    @Override
    public String format(Lexer lexer, Writer writer) throws Exception {
        FormatterState formatterState = FormatterState.LINE_START;
        ctx.setContextBuilder(content);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.nextToken();
            repo.getCommand(formatterState, token).execute(token, ctx);
            formatterState = transitions.nextState(formatterState, token);
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


