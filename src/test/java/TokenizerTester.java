import org.example.Tokenizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TokenizerTester {

    @Test
    public void tokenizeSimpleString() {
        var actual = Tokenizer.tokenize(
                "hello"
        );
        var expected = Arrays.asList("hello");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tokenizeSingleBracket() {
        var actual = Tokenizer.tokenize(
                "2[abc]"
        );
        var expected = Arrays.asList("2[abc]");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tokenizeComplexString() {
        var actual = Tokenizer.tokenize(
                "xxx1[abc]yyy2[def]z"
        );
        var expected = Arrays.asList("xxx", "1[abc]", "yyy", "2[def]", "z");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tokenizeNestedBrackets() {
        var actual = Tokenizer.tokenize(
                "2[3[x]y]"
        );
        var expected = Arrays.asList("2[3[x]y]");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tokenizeEmptyString() {
        Tokenizer.tokenize("");
    }
}
