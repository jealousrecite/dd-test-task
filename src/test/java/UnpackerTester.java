import org.example.Unpacker;

import org.junit.Assert;
import org.junit.Test;

public class UnpackerTester {

    @Test
    public void simpleString() {
        Assert.assertEquals(
                "hello",
                Unpacker.unpack("hello")
        );
    }

    @Test
    public void singleBrackets() {
        Assert.assertEquals(
                "abcabc",
                Unpacker.unpack("2[abc]")
        );
    }

    @Test
    public void multipleBrackets() {
        Assert.assertEquals(
                "xyzxyzxyzxyxyxyxyz",
                Unpacker.unpack("3[xyz]4[xy]z")
        );
    }

    @Test
    public void emptyBrackets() {
        Assert.assertEquals(
                "",
                Unpacker.unpack("3[]")
        );
    }

    @Test
    public void zeroRepetitions() {
        Assert.assertEquals(
                "",
                Unpacker.unpack("0[abc]")
        );
    }
    
    @Test
    public void nestedBrackets() {
        Assert.assertEquals(
                "xxxyxxxy",
                Unpacker.unpack("2[3[x]y]"));
    }

    @Test
    public void deepNesting() {
        Assert.assertEquals(
                "aabbbccaabbbccmiddletextxyzzzyyzzzyxxyzzzyyzzzyxxyzzzyyzzzyx",
                Unpacker.unpack("2[aa3[b]cc]middletext3[x2[y3[z]y]x]")
        );
    }

}
