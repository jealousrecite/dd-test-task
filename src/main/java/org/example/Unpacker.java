package org.example;


public class Unpacker {

    public static void main(String[] args) {
        var testData = "3[xyz]4[xy]z";

        System.out.println("Demo Result: " + unpack(testData));
    }

    public static String unpack(String s) {
        return TokenNode.makeTree(s)
                .unpackTree();
    }

}