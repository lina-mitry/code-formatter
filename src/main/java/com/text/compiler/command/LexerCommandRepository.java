package com.text.compiler.command;

import com.text.compiler.command.commands.lexer.AppendLexemeCommand;
import com.text.compiler.command.commands.lexer.CloseFiguredBracketCommand;
import com.text.compiler.command.commands.lexer.CloseRoundBracketCommand;
import com.text.compiler.command.commands.lexer.CloseSquareBracketCommand;
import com.text.compiler.command.commands.lexer.CommaCommand;
import com.text.compiler.command.commands.lexer.DoNothingCommand;
import com.text.compiler.command.commands.lexer.DotCommand;
import com.text.compiler.command.commands.lexer.EqualSignCommand;
import com.text.compiler.command.commands.lexer.ForCommand;
import com.text.compiler.command.commands.lexer.IfCommand;
import com.text.compiler.command.commands.lexer.NewLineCommand;
import com.text.compiler.command.commands.lexer.OpenFiguredBracketCommand;
import com.text.compiler.command.commands.lexer.OpenRoundBracketCommand;
import com.text.compiler.command.commands.lexer.OpenSquareBracketCommand;
import com.text.compiler.command.commands.lexer.PlusSignCommand;
import com.text.compiler.command.commands.lexer.PostponeCommand;
import com.text.compiler.command.commands.lexer.SemicolonCommand;
import com.text.compiler.command.commands.lexer.SpacingCommand;
import com.text.compiler.command.commands.lexer.TextCommand;
import com.text.compiler.context.IContextLexer;
import com.text.compiler.state.LexerState;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Slf4j
public class LexerCommandRepository {
    private final Map<Pair<LexerState, Character>, Command<Character, IContextLexer>> commandMap = new HashMap<>();

    public LexerCommandRepository() {
        fillForCommands();
        fillIfCommands();
        fillDelimitersCommands();
        fillTextCommands();
    }

    private void fillForCommands() {
        commandMap.put(new Pair<>(LexerState.DEFAULT, 'f'), new AppendLexemeCommand());
        commandMap.put(new Pair<>(LexerState.FOR1, 'o'), new AppendLexemeCommand());
        commandMap.put(new Pair<>(LexerState.FOR2, 'r'), new ForCommand());
        commandMap.put(new Pair<>(LexerState.FOR3, ' '), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.FOR3, '('), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.FOR1, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.FOR2, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.FOR3, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.FOR1, ' '), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.FOR2, ' '), new PostponeCommand());
    }

    private void fillIfCommands() {
        commandMap.put(new Pair<>(LexerState.DEFAULT, 'i'), new AppendLexemeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, 'f'), new IfCommand());
        commandMap.put(new Pair<>(LexerState.IF2, ' '), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF2, '('), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, ';'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, ' '), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, '+'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, ','), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.IF1, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.IF2, null), new TextCommand());

    }

    private void fillDelimitersCommands() {
        commandMap.put(new Pair<>(LexerState.DEFAULT, '('), new OpenRoundBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, ')'), new CloseRoundBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '{'), new OpenFiguredBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '}'), new CloseFiguredBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, ';'), new SemicolonCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '\n'), new NewLineCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '='), new EqualSignCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, ','), new CommaCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '+'), new PlusSignCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '['), new OpenSquareBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, ']'), new CloseSquareBracketCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, '.'), new DotCommand());
        commandMap.put(new Pair<>(LexerState.DEFAULT, ' '), new SpacingCommand());
        commandMap.put(new Pair<>(LexerState.SPACING, ' '), new DoNothingCommand());
        commandMap.put(new Pair<>(LexerState.SPACING, null), new PostponeCommand());
    }

    private void fillTextCommands() {
        commandMap.put(new Pair<>(LexerState.DEFAULT, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, null), new TextCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, ' '), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, ';'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '('), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, ')'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '{'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '}'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, ','), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '='), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '+'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '['), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, ']'), new PostponeCommand());
        commandMap.put(new Pair<>(LexerState.TEXT, '.'), new PostponeCommand());
    }

    public Command<Character, IContextLexer> getCommand(LexerState lexerState, Character ch) {
        //log.debug("Get command by state: {} and char: {}", state.getState(), ch);
        Command<Character, IContextLexer> command = commandMap.get(new Pair<>(lexerState, ch));
        return (command == null)
                ? commandMap.get(new Pair<>(lexerState, (Character) null))
                : command;
    }
}
