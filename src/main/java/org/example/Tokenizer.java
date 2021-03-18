package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tokenizer {

    /*
    the result contains two types of tokens:
    plain string [a-zA-Z]+
    and
    <count>[<string>]
    e.g. '3[xyz]4[xy]z' is tokenized as follows:
    '3[xyz]', '4[xy]', 'z'
    */
    public static List<String> tokenize(String s) {
        var tokens = new ArrayList<String>();

        while (!s.isEmpty()) {
            var tokenEndIndex = skipToken(s);
            var token = s.substring(0, tokenEndIndex);
            tokens.add(token);
            s = s.substring(tokenEndIndex);
        }

        return tokens;
    }

    private static int skipToken(String s) {
        var wordEndIndex = skipWord(s);
        if (wordEndIndex > 0) {
            return wordEndIndex;
        }

        var numberEndIndex = skipNumber(s);
        var stringWithNumberSkipped = s.substring(numberEndIndex);
        var bracketEndIndex = skipBracket(stringWithNumberSkipped);

        // failed to find any valid token in the nonempty string
        if (numberEndIndex == 0 || bracketEndIndex == 0) {
            throw new IllegalArgumentException("Failed to tokenize '" + s + "'");
        }

        return numberEndIndex + bracketEndIndex;
    }

    public static int skipWord(String s) {
        return skipByPredicate(s, Character::isAlphabetic);
    }

    public static int skipNumber(String s) {
        return skipByPredicate(s, Character::isDigit);
    }

    public static int skipBracket(String s) {
        if (s.isEmpty() || (s.charAt(0) != '[')) {
            return 0;
        }

        var bracketCounter = 1;
        var endIndex = 1;

        while (endIndex < s.length() && bracketCounter > 0) {
            var currentChar = s.charAt(endIndex);

            if (currentChar == '[') {
                ++bracketCounter;
            } else if (currentChar == ']') {
                --bracketCounter;
            }

            ++endIndex;
        }

        return bracketCounter == 0 ? endIndex : 0;
    }

    private static int skipByPredicate(String s, Predicate<Character> predicate) {
        var charArray = s.toCharArray();
        var endIndex = 0;
        while (endIndex < s.length() && predicate.test(charArray[endIndex])) {
            endIndex++;
        }
        return endIndex;
    }
}
