package com.text.compiler.command;

import com.text.compiler.command.commands.formatter.AppendTokenCommand;
import com.text.compiler.command.commands.formatter.DoNothingCommand;
import com.text.compiler.command.commands.formatter.IndentDecrementCommand;
import com.text.compiler.command.commands.formatter.IndentIncrementCommand;
import com.text.compiler.command.commands.formatter.SemicolonCommand;
import com.text.compiler.context.IContextFormatter;
import com.text.compiler.state.FormatterState;
import com.text.compiler.state.LexerState;
import com.text.compiler.token.IToken;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Slf4j
public class FormatterCommandRepository {
    private final Map<Pair<FormatterState, String>, Command<IToken, IContextFormatter>> commandMap = new HashMap<>();

    public FormatterCommandRepository() {
        fillCommands();
    }

    void fillCommands() {
        commandMap.put(new Pair<>(FormatterState.LINE_START, null), new AppendTokenCommand());
        commandMap.put(new Pair<>(FormatterState.CODE, null), new AppendTokenCommand());
        commandMap.put(new Pair<>(FormatterState.CODE, LexerState.OPEN_FIGURE_BRACKETS.getState()), new IndentIncrementCommand());
        commandMap.put(new Pair<>(FormatterState.CODE, LexerState.CLOSE_FIGURE_BRACKETS.getState()), new IndentDecrementCommand());
        commandMap.put(new Pair<>(FormatterState.CODE, LexerState.SEMICOLON.getState()), new SemicolonCommand());
        commandMap.put(new Pair<>(FormatterState.LINE_START, LexerState.SPACING.getState()), new DoNothingCommand());
        commandMap.put(new Pair<>(FormatterState.CODE, LexerState.NEW_LINE.getState()), new DoNothingCommand());
        commandMap.put(new Pair<>(FormatterState.LINE_START, LexerState.NEW_LINE.getState()), new DoNothingCommand());
    }

    public Command<IToken, IContextFormatter> getCommand(FormatterState formatterState, IToken token) {
        Command<IToken, IContextFormatter> command = commandMap.get(new Pair<>(formatterState, token.getName()));
        return (command == null)
                ? commandMap.get(new Pair<>(formatterState, (String) null))
                : command;
    }
}
