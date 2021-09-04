import com.text.compiler.formatter.StateMachineFormatter;
import com.text.compiler.io.Reader;
import com.text.compiler.io.StringReader;
import com.text.compiler.io.StringWriter;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.Lexer;
import com.text.compiler.lexer.StateMachineLexer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class FormatterTest {
    private static final String TEST_STRING = "for()   {bbb;} if(a){fork;}";
    private static final String EXPECTED_TEST_STRING =
            "for() {\n" +
            "    bbb;\n" +
            "}\n" +
            "if(a){\n" +
            "    fork;\n" +
            "}\n";
    private Writer writer;
    private Reader reader;
    private Lexer lexer;

    @Before
    public void setUp() {
        reader = new StringReader(TEST_STRING);
        lexer = new StateMachineLexer(reader);
        writer = new StringWriter();
    }

    @Test
    public void test() throws Exception {
        StateMachineFormatter formatter = new StateMachineFormatter();

        Assertions.assertEquals(EXPECTED_TEST_STRING, formatter.format(lexer, writer));
    }
}
