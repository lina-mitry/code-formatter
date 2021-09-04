package com.text.compiler.context;

import com.text.compiler.token.IToken;

public interface IContextFormatter {
    void writeLexeme(IToken token);

    void writeNewLine();

    void writeIndent();

    void incrementIndent();

    void decrementIndent();
}
