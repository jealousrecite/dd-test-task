import org.example.Validator;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTester {

    private void assertValid(String s) {
        Assert.assertTrue(
                Validator.isValidPackedString(s)
        );
    }

    private void assertInvalid(String s) {
        Assert.assertFalse(
                Validator.isValidPackedString(s)
        );
    }

    @Test
    public void simpleStringIsValid() {
        assertValid("hello");
    }

    @Test
    public void singleBracketsStringIsValid() {
        assertValid("2[xyz]");
    }

    @Test
    public void emptyStringIsValid() {
        assertValid("");
    }

    @Test
    public void specialCharactersStringIsInvalid() {
        assertInvalid("h*llo");
    }

    @Test
    public void missingClosingBracketStringIsInvalid() {
        assertInvalid("2[xyz");
    }

    @Test
    public void extraClosingBracketStringIsInvalid() {
        assertInvalid("2[xyz]]");
    }
}
