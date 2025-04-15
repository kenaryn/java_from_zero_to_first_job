package javacore.userinput;

//import java.math.BigDecimal;
import java.util.Scanner;

import static java.lang.Double.NaN;

public class TriangleArea {
    public static void main(String[] args) {
        System.out.print("Enter the side A's length of a triangle: ");
        Scanner scan = new Scanner(System.in);

//        BigDecimal a = new BigDecimal("0.00");
//        BigDecimal b = new BigDecimal("0.00");
//        BigDecimal c = new BigDecimal("0.00");
        int a = 0; int b = 0; int c = 0;
        try {
            a = Integer.parseInt(scan.nextLine());
            System.out.print("Now enter the side B's length: ");
            b = Integer.parseInt(scan.nextLine());
            System.out.print("Then enter the side C's length: ");
            c = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Invalid format argument: " + ex.getMessage());
        }

        // Calculates the semi-perimeter.
        int p = (a + b + c) / 2;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        if(!(Double.isNaN(area))) {
            System.out.println("Rectangle's area:" + area + " cm²");
        } else {
            System.out.println("Invalid value for one or more side(s).");
        }
    }
}
