//import com.text.compiler.exceptions.CloseException;
//import com.text.compiler.exceptions.ReaderException;
//import com.text.compiler.exceptions.ValidationException;
//import com.text.compiler.exceptions.WriterException;
//import com.text.compiler.formatter.Formatter;
//import com.text.compiler.formatter.SimpleFormatter;
//import com.text.compiler.io.*;
//import com.text.compiler.lexer.SimpleLexer;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import java.io.File;
//
//public class FormatterTest {
//    private Formatter formatter;
//    private static final String PAIR_BRACKET = "if(aaa)\n{bbb;if(aaa){bbb;}}";
//    private static final String NOT_PAIR_BRACKET = "if(aaa){bbb;if(aaa)\n{bbb;}";
//    private static final String FIRST_CLOSE_BRACKET = "if(aaa)}\nbbb;if(aaa)\n{bbb;}";
//    private static final String DIFFERENT_BRACKET_SEQUENCE = "if(aaa)}bbb;\nif(aaa)}bbb;if{{";
//    private static final String ONE_LINE_EXPRESSION = "if(aaa){bbb;if(aaa){bbb;}}";
//    private static final String TEST_OUTPUT_FILE = "resources/output.txt";
//    private static final String FILE_NOT_FOUND = "text.txt";
//
//    @Before
//    public void configure() {
//        this.formatter = new SimpleFormatter();
//    }
//
//    @Test
//    public void checkNotPairBrackets() throws CloseException, WriterException, ReaderException {
//        try (Reader reader = new StringReader(PAIR_BRACKET);
//             Writer writer = new StringWriter()) {
//            SimpleLexer lexer = new SimpleLexer(reader);
//            Assertions.assertDoesNotThrow(() -> formatter.format(lexer, writer));
//        }
//
//    }
//
//    @Test
//    public void checkPairBrackets() throws CloseException, WriterException, ReaderException {
//        try (Reader reader = new StringReader(NOT_PAIR_BRACKET)) {
//            Assertions.assertThrows(ValidationException.class, () -> new SimpleLexer(reader));
//        }
//    }
//
//    @Test
//    public void checkFirstClosePairBrackets() throws CloseException, WriterException, ReaderException {
//        try (Reader reader = new StringReader(FIRST_CLOSE_BRACKET)) {
//            Assertions.assertThrows(ValidationException.class, () ->new SimpleLexer(reader));
//        }
//    }
//
//    @Test
//    public void checkDifferentBracketsSequence() throws CloseException, WriterException, ReaderException {
//        try (Reader reader = new StringReader(DIFFERENT_BRACKET_SEQUENCE)) {
//            Assertions.assertThrows(ValidationException.class, () -> new SimpleLexer(reader));
//        }
//    }
//
//    @Test
//    public void checkOneLineExpression() throws CloseException, WriterException, ReaderException {
//        try (Reader reader = new StringReader(ONE_LINE_EXPRESSION);
//             Writer writer = new StringWriter()) {
//            SimpleLexer lexer = new SimpleLexer(reader);
//            Assertions.assertDoesNotThrow(() -> formatter.format(lexer, writer));
//        }
//    }
//
//    @Test
//    public void fileNotFoundException() {
//        Assertions.assertThrows(ReaderException.class, () -> new FileReader(new File(FILE_NOT_FOUND)));
//    }
//
//}
