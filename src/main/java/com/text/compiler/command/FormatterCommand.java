package com.text.compiler.command;

import com.text.compiler.context.IContextFormatter;
import com.text.compiler.token.IToken;

public interface FormatterCommand {
    void execute(IToken token, IContextFormatter formatterContext);
}
