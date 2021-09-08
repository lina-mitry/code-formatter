import com.text.compiler.formatter.Formatter;
import com.text.compiler.io.Reader;
import com.text.compiler.io.StringReader;
import com.text.compiler.io.StringWriter;
import com.text.compiler.io.Writer;
import com.text.compiler.lexer.ILexer;
import com.text.compiler.lexer.Lexer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class FormatterTest {
    private static final String TEST = "for()   {ddd;} if(a) {fork;nnn;if(a) {aaa;if(a) {bbb=ccc;}\nelse      {aaa;}}}";
    private static final String EXPECTED_TEST ="for() {\n" +
            "    ddd;\n" +
            "}\n" +
            "if(a) {\n" +
            "    fork;\n" +
            "    nnn;\n" +
            "    if(a) {\n" +
            "        aaa;\n" +
            "        if(a) {\n" +
            "            bbb=ccc;\n" +
            "        }\n" +
            "        else {\n" +
            "            aaa;\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
    private Writer writer;
    private ILexer lexer;

    @Before
    public void setUp() {
        Reader reader = new StringReader(TEST);
        lexer = new Lexer(reader);
        writer = new StringWriter();
    }

    @Test
    public void test() throws Exception {
        Formatter formatter = new Formatter();

        Assertions.assertEquals(EXPECTED_TEST, formatter.format(lexer, writer));
    }
}
