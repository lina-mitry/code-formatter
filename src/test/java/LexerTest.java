import com.text.compiler.exceptions.CloseException;
import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.exceptions.WriterException;
import com.text.compiler.io.Reader;
import com.text.compiler.io.StringReader;
import com.text.compiler.lexer.StateMachineLexer;
import com.text.compiler.token.IToken;
import com.text.compiler.token.Token;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LexerTest {
    private static final String TEST = "for()   {bbb;}if(a){fork; fo;}";
    private Reader reader;

    @Before
    public void configure() {
        reader = new StringReader(TEST);
    }

    @Test
    void test() throws CloseException, WriterException, ReaderException {
        StateMachineLexer lexer = new StateMachineLexer(reader);

        IToken forLoop = lexer.nextToken();
        Assertions.assertEquals("FOR3", forLoop.getName());
        Assertions.assertEquals("for", forLoop.getLexeme());

        IToken openRoundBracket = lexer.nextToken();
        Assertions.assertEquals("OPEN_ROUND_BRACKETS", openRoundBracket.getName());
        Assertions.assertEquals("(", openRoundBracket.getLexeme());

        IToken closeRoundBracket =  lexer.nextToken();
        Assertions.assertEquals("CLOSE_ROUND_BRACKETS", closeRoundBracket.getName());
        Assertions.assertEquals(")", closeRoundBracket.getLexeme());

        IToken space = lexer.nextToken();
        Assertions.assertEquals("SPACING", space.getName());
        Assertions.assertEquals(" ", space.getLexeme());

        IToken openFigureBracket =  lexer.nextToken();
        Assertions.assertEquals("OPEN_FIGURE_BRACKETS", openFigureBracket.getName());
        Assertions.assertEquals("{", openFigureBracket.getLexeme());

        IToken textB =  lexer.nextToken();
        Assertions.assertEquals("TEXT", textB.getName());
        Assertions.assertEquals("bbb", textB.getLexeme());

        IToken semicolon =  lexer.nextToken();
        Assertions.assertEquals(";", semicolon.getName());
        Assertions.assertEquals("SEMICOLON", semicolon.getLexeme());

        IToken closeFigureBracket =  lexer.nextToken();
        Assertions.assertEquals("CLOSE_FIGURE_BRACKETS", closeFigureBracket.getName());
        Assertions.assertEquals("}", closeFigureBracket.getLexeme());

        IToken b =  lexer.nextToken();
        Assertions.assertEquals("IF2", b.getName());
        Assertions.assertEquals("if", b.getLexeme());

        IToken openRoundBracket2 = lexer.nextToken();
        Assertions.assertEquals("OPEN_ROUND_BRACKETS", openRoundBracket2.getName());
        Assertions.assertEquals("(", openRoundBracket2.getLexeme());

        IToken textA =  lexer.nextToken();
        Assertions.assertEquals("TEXT", textA.getName());
        Assertions.assertEquals("a", textA.getLexeme());

        IToken closeRoundBracket2 =  lexer.nextToken();
        Assertions.assertEquals("CLOSE_ROUND_BRACKETS", closeRoundBracket2.getName());
        Assertions.assertEquals(")", closeRoundBracket2.getLexeme());

        IToken open =  lexer.nextToken();
        Assertions.assertEquals("{", open.getName());
        Assertions.assertEquals("OpenBracket", open.getLexeme());

        IToken openFigureBracket2 =  lexer.nextToken();
        Assertions.assertEquals("OPEN_FIGURE_BRACKETS", openFigureBracket2.getName());
        Assertions.assertEquals("{", openFigureBracket2.getLexeme());

        IToken textFork =  lexer.nextToken();
        Assertions.assertEquals("TEXT", textFork.getName());
        Assertions.assertEquals("fork", textFork.getLexeme());

        IToken semicolon2 =  lexer.nextToken();
        Assertions.assertEquals(";", semicolon2.getName());
        Assertions.assertEquals("SEMICOLON", semicolon2.getLexeme());

        IToken textFo =  lexer.nextToken();
        Assertions.assertEquals("TEXT", textFo.getName());
        Assertions.assertEquals("fo", textFo.getLexeme());

        IToken semicolon3 =  lexer.nextToken();
        Assertions.assertEquals(";", semicolon3.getName());
        Assertions.assertEquals("SEMICOLON", semicolon3.getLexeme());

        IToken closeFigureBracket2 =  lexer.nextToken();
        Assertions.assertEquals("CLOSE_FIGURE_BRACKETS", closeFigureBracket2.getName());
        Assertions.assertEquals("}", closeFigureBracket2.getLexeme());
    }
}