package org.example;


public class Unpacker {

    public static void main(String[] args) {
        if (args.length == 2) {
            var userInput = args[1];
            try {
                System.out.println(Unpacker.unpack(userInput) + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input\n"
                        + e.getMessage() + "\n");
            }
        } else if (args.length > 2) {
            System.out.println("Too many arguments\n");
            System.out.println(getExample());
        } else {
            System.out.println("No arguments provided\n");
            System.out.println(getExample());
        }
    }

    private static String getExample() {
        var exampleInput = "3[xyz]4[xy]z";
        return "Example input: " + exampleInput + "\n"
                + "Example output: " + Unpacker.unpack(exampleInput) + "\n";
    }

    public static String unpack(String s) throws IllegalArgumentException {
        return TokenNode.makeTree(s)
                .unpackTree();
    }

}