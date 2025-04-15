package javacore.operators;

import java.util.Arrays;
import java.util.Scanner;

public class JavaTypes {
    public static void main(String[] args) {
//        byte b = 3;
//        short s = 3;
//        int i = 3;
//        long l = 3L;
//
//        float f = 3.0F;
//        double d = 3.0;
//
//        char c = 3;
//        byte byteExpressionType = (byte) (b + b);
//        int intExpressionType = (int) (i + l);

        Integer i1 = 128;
        Integer i2 = 128;
//        System.out.println("i1 == i2: " + (i1 == i2));
        System.out.println(i1 == i2);

        // Use of Integer pool to reduce unnecessary memory allocation due to unboxing.
        Integer i3 = 127;
        Integer i4 = 127;
        System.out.println(i3 == i4);
        i4 = -128;
        System.out.println(i3 == i4);

//        Integer i5 = new Integer(11);
        Integer i5 = Integer.valueOf(3);
        Integer i6 = Integer.valueOf(3);
        System.out.println(i6.equals(i5));  // true

        int[] arr1 = {3, 9, 27};
        int[] arr2 = {3, 9, 27};
        System.out.println("arr1 == arr2: " + (arr1 == arr2));  // false
        System.out.println("arr1.equals(arr2): " + (arr1.equals(arr2)));  // false
        System.out.println("Arrays.equals(arr1, arr2): " + (Arrays.equals(arr1, arr2)));  // true

        arr2 = arr1;
        System.out.println(arr1 == arr2); // true

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your input: ");
        var input = scan.nextLine();
        System.out.println(input);
    }
}
