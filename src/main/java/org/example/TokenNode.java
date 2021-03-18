package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class TokenNode {

    private final String rawValue;
    private final int repetitionCount;
    private final List<TokenNode> children;

    public static TokenNode makeTree(String s) {
        // create artificial root node
        return new TokenNode("1[" + s + "]");
    }

    private TokenNode(String s) {

        if (isPlainString(s)) {

            rawValue = s;
            repetitionCount = 1;
            children = null;

        } else if (isPackedString(s)) {

            rawValue = null;
            var countEndIndex = Tokenizer.skipNumber(s);
            repetitionCount = Integer.parseInt(s.substring(0, countEndIndex));
            // trim number
            s = s.substring(countEndIndex);
            // trim leading and trailing brackets
            s = s.substring(1, s.length() - 1);

            children = Tokenizer.tokenize(s)
                    .stream()
                    .map(TokenNode::new)
                    .collect(Collectors.toList());
        } else {

            throw new IllegalArgumentException("Provided string is not a valid token");

        }

    }

    private boolean isPlainString(String s) {
        return Tokenizer.skipWord(s) == s.length();
    }

    private boolean isPackedString(String s) {
        var numberEndIndex = Tokenizer.skipNumber(s);
        if (numberEndIndex == 0) {
            return false;
        }

        var bracketEndIndex = Tokenizer.skipBracket(s.substring(numberEndIndex));
        return bracketEndIndex != 0
                && numberEndIndex + bracketEndIndex == s.length();
    }

    public String unpackTree() {
        // if the token is a plain string
        if (children == null) {
            return rawValue;
        }

        // unpack and concatenate all children
        var childrenConcat = children.stream()
                .map(TokenNode::unpackTree)
                .reduce(String::concat)
                .orElse("");

        return childrenConcat.repeat(repetitionCount);
    }
}
