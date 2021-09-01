import com.text.compiler.exceptions.ReaderException;
import com.text.compiler.io.StringReader;
import com.text.compiler.lexer.StateMachineLexer;
import com.text.compiler.token.IToken;
import com.text.compiler.token.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LexerTest {
    private static final String FOR = "for(for(";
    private static List<IToken> FOR_TOKENS;

    @Before
    public void configure() {
        fillForTokens();
    }

    private static void fillForTokens() {
        FOR_TOKENS = new ArrayList<>() {{
            add(new Token("for", "for"));
            add(new Token("Open_Round_Bracket", "("));
            add(new Token("for", "for"));
            add(new Token("Open_Round_Bracket", "("));
        }};
    }

    @Test
    public void forTokenTest() throws ReaderException {
        try (var reader = new StringReader(FOR)) {
            var lexer = new StateMachineLexer(reader);
            var tokens = new ArrayList<IToken>();
            while (lexer.hasMoreTokens()) {
                tokens.add(lexer.nextToken());
            }
            Assert.assertEquals(FOR_TOKENS, tokens);
        }
    }
}
