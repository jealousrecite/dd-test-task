package org.example;

public class Validator {

    public static boolean isValidPackedString(String s) {
        try {
            var tokens = Tokenizer.tokenize(s);
            return tokens.stream()
                    .mapToInt(String::length)
                    .sum() == s.length();
        }
        catch (Exception e) {
            System.err.println("Validator failed to tokenize the input string");
            System.err.println(e.getMessage());
        }
        return false;
    }
}
