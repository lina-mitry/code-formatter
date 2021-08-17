import com.text.compiler.exceptions.ValidationException;
import com.text.compiler.formatter.Formatter;
import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.reader.StringReader;
import com.text.compiler.validator.SimpleValidator;
import com.text.compiler.writer.StringWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class FormatterTest {
    private Formatter formatter;
    private static final String PAIR_BRACKET = "if(aaa)\n{bbb;if(aaa){bbb;}}";
    private static final String NOT_PAIR_BRACKET = "if(aaa){bbb;if(aaa)\n{bbb;}";
    private static final String FIRST_CLOSE_BRACKET = "if(aaa)}\nbbb;if(aaa)\n{bbb;}";
    private static final String DIFFERENT_BRACKET_SEQUENCE = "if(aaa)}bbb;\nif(aaa)}bbb;if{{";
    private static final String ONE_LINE_EXPRESSION = "if(aaa){bbb;if(aaa){bbb;}}";

    @Before
    public void configure() {
        this.formatter = new SimpleFormatter(new SimpleValidator());
    }

    @Test
    public void checkNotPairBrackets() {
        try (var reader = new StringReader(PAIR_BRACKET);
             var writer = new StringWriter()) {
            Assertions.assertDoesNotThrow(() -> formatter.format(reader, writer));
        }

    }

    @Test
    public void checkPairBrackets() {
        try (var reader = new StringReader(NOT_PAIR_BRACKET);
             var writer = new StringWriter()) {
            Assertions.assertThrows(ValidationException.class, () -> formatter.format(reader, writer));
        }
    }

    @Test
    public void checkFirstClosePareBrackets() {
        try (var reader = new StringReader(FIRST_CLOSE_BRACKET);
             var writer = new StringWriter()) {
            Assertions.assertThrows(ValidationException.class, () -> formatter.format(reader, writer));
        }
    }

    @Test
    public void checkDifferentBracketsSequence() {
        try (var reader = new StringReader(DIFFERENT_BRACKET_SEQUENCE);
             var writer = new StringWriter()) {
            Assertions.assertThrows(ValidationException.class, () -> formatter.format(reader, writer));
        }
    }

    @Test
    public void checkOneLineExpression() {
        try (var reader = new StringReader(ONE_LINE_EXPRESSION);
             var writer = new StringWriter()) {
            Assertions.assertDoesNotThrow(() -> formatter.format(reader, writer));
        }
    }

}