import org.example.Unpacker;
import org.junit.Test;

public class ValidityTester {

    @Test(expected = IllegalArgumentException.class)
    public void foreignSymbolsStringIsInvalid() {
        Unpacker.unpack("h*llo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void extraOpeningBracketStringIsInvalid() {
        Unpacker.unpack("2[[abc]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void extraClosingBracketStringIsInvalid() {
        Unpacker.unpack("2[abc]]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingOpeningBracketStringIsInvalid() {
        Unpacker.unpack("2abc]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingClosingBracketStringIsInvalid() {
        Unpacker.unpack("2[abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void noCounterStringIsInvalid() {
        Unpacker.unpack("[abc]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void numbersInInappropriatePlaceStringIsInvalid() {
        Unpacker.unpack("2[ab3c]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void numbersOnlyStringIsInvalid() {
        Unpacker.unpack("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nestedForeignSymbolsStringIsInvalid() {
        Unpacker.unpack("abc2[x&z]def");
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringWithSpacesIsInvalid() {
        Unpacker.unpack("abc 2[xyz]");
    }
}
