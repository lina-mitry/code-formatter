package com.text.compiler.state;

public interface State {

    enum Common implements State {
        DEFAULT,
        UNKNOWN
    }

    enum For implements State {
        F,
        FO,
        TERMINATE
    }

    enum If implements State {
        I,
        TERMINATE
    }
}
