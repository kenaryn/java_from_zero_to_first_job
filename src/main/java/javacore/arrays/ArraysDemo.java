package javacore.arrays;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        int[] arr;
//        System.out.println(arr);
        int arr2[];

        arr = new int[3];
        System.out.println(Arrays.toString(arr) + " has a length of " + arr.length);
        System.out.println(arr[0]);

        double[] arr3 = new double[10];
        System.out.println(arr3[3]);

        boolean[] arr4 = new boolean[10];
        System.out.println("boolean arr4[0]: " + arr4[0]);
//        System.out.println(arr3[10]);

        int[] arr5 = {27, 9, 3};
        int[][] matrix = {
            {0, -6, 5},
            {2, -3, 7},
            {5, 12, 11},
        };
        System.out.println(matrix[0][2]);  // 5


//        Arrays.sort(arr5);
//        System.out.println(Arrays.toString(arr5));  // [3, 9, 27]
        Arrays.sort(Arrays.copyOf(arr5, arr5.length));
        System.out.println(Arrays.toString(arr5));

        System.out.println("-----------");
        int[][] matrix2 = new int[10][];
//        System.out.println(Arrays.toString(matrix2[0]));  // []
        System.out.println(matrix2[0]);  // []
    }
}
