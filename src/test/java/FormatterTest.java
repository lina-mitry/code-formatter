import com.text.compiler.formatter.Formatter;
import com.text.compiler.formatter.SimpleFormatter;
import com.text.compiler.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FormatterTest {
    private static Formatter formatter;
    private static final String PAIR_BRACKETS = "formatter/pairBrackets.txt";
    private static final String NOT_PAIR_BRACKETS = "formatter/notPairBrackets.txt";
    private static final String FIRST_CLOSE_BRACKETS = "formatter/firstCloseBrackets.txt";
    private static final String ONE_LINE_BRACKETS = "formatter/oneLineBrackets.txt";
    private static final String RESULT = "/formatter/result.txt";

    @Before
    public void configure() {
        formatter = new SimpleFormatter();
    }

    @Test
    public void checkPairBrackets() {
        try {
            formatter.format(PAIR_BRACKETS, RESULT);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void checkNotPairBrackets() {
        assertThrows(ValidationException.class, () -> {
            formatter.format(NOT_PAIR_BRACKETS, RESULT);
        });
    }

    @Test
    public void checkFirstClosePareBrackets() {
        assertThrows(ValidationException.class, () -> {
            formatter.format(FIRST_CLOSE_BRACKETS, RESULT);
        });
    }

    @Test
    public void checkOneLineBrackets() {
        try {
            formatter.format(ONE_LINE_BRACKETS, RESULT);
        } catch (Exception e) {
            fail();
        }
    }
}
