package javacore.strings;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class ExoString {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        formatter.format("%.1f", Math.TAU);
        System.out.println(formatter);

        formatter = new Formatter();
        formatter.format("%.2f", Math.TAU);
        System.out.println(formatter);

        formatter = new Formatter();
        formatter.format("%.3f", Math.TAU);
        System.out.println(formatter);

        formatter = new Formatter();
        formatter.format("%.4f", Math.TAU);
        System.out.println(formatter);

        formatter = new Formatter();
        formatter.format("%.6f", Math.TAU);
        System.out.println(formatter);

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter any text: ");
        String inputText = scan.nextLine().trim();
//        String[] words = inputText.split("[\\.,!?\\s-]+");
        String[] words = inputText.split("[\\p{P}\\s]+");
        System.out.println(Arrays.toString(words));

        System.out.println(String.join("", words));
    }
}
