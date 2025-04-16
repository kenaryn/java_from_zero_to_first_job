package javacore.userinput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CircleCircumference {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a circle's radius: ");

        BigDecimal radius = new BigDecimal("0.00");
        try {
            String input = scan.nextLine();
            radius = new BigDecimal(input);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid format argument: " + ex.getMessage());
        }

        BigDecimal circumference = radius.multiply(BigDecimal.valueOf(Math.TAU)).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Circle's circumference: " + circumference + "cm²");
    }
}
