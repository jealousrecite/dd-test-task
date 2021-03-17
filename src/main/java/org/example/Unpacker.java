package org.example;


public class Unpacker {

    public static void main(String[] args) {
        var testData = "3[xyz]4[xy]z";
        System.out.println("Result: " + unpack(testData));
    }

    public static String unpack(String s) {
        var resultBuilder = new StringBuilder();

        for (var token : Tokenizer.tokenize(s)) {

            var isTokenPlainString = Character.isAlphabetic(token.charAt(0));
            if (isTokenPlainString) {
                resultBuilder.append(token);
                continue;
            }
            // else the token looks like <count>[<possiblyPackedString>]
            token = token.substring(0, token.length() - 1); // remove the trailing ]
            // limit to prevent splitting around the matches in the inner substring
            var parts = token.split("\\[", 2);
            var count = Integer.parseInt(parts[0]);
            // as the inner string may be packed, unpack it before appending to the result
            var stringToRepeat = unpack(parts[1]);
            resultBuilder.append(stringToRepeat.repeat(count));

        }

        return resultBuilder.toString();
    }

}