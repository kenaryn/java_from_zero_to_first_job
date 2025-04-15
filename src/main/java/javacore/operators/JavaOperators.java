package javacore.operators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JavaOperators {
    public static void main(String[] args) {
        System.out.println("----- Unary operators -----");
        int i = 12;
        int i1 = ++i;
        System.out.println("++i = " + i1);
        System.out.println("i = " + i);
        int i2 = i++;
        System.out.println("i++ = " + i2);
        int i3 = --i;
        System.out.println("--i = " + i3);
        int i4 = i--;
        System.out.println("i-- = " + i4);
        System.out.println("i = " + i);

//        System.out.println(false & (5 / 0 == 0));   // ArithmeticException
        System.out.println(false && (5 / 0 == 0));  // false (thanks to short-circuit mechanism)
        System.out.println(true ^ true);   // false
        System.out.println(true ^ false);  // true

        BigDecimal fee = new BigDecimal("20.00");
        BigDecimal amountOfPersons = new BigDecimal("3");
        BigDecimal charge = fee.divide(amountOfPersons, RoundingMode.HALF_UP);
        System.out.println("Charge per person: " + charge + "$");

        BigDecimal bd1 = new BigDecimal("3.1");
        BigDecimal bd2 = new BigDecimal("1.21");
        System.out.println("3.1 - 1.21 = " + bd1.subtract(bd2));

        System.out.println(Math.TAU);
        System.out.println(Math.max(3, 5));
        System.out.println(Math.min(-6, 3));
        System.out.println(Math.sqrt(7));
        System.out.println(Math.abs(-9));

        System.out.println(Math.sqrt(-7));
        System.out.println(5 / 0.0);
        System.out.println(-5 / 0.0);

        System.out.println(Math.round(20.0 / 3.0));
        System.out.println(Math.round(20.0 * 100.0 / 3.0) / 100.0);

        System.out.println(Math.random());
        System.out.println((int) (Math.random() * 100));
        System.out.println((int) (Math.random() * 100) + 100);
    }
}
