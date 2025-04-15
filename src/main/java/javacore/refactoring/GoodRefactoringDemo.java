package javacore.refactoring;

import java.util.Arrays;

public class GoodRefactoringDemo {
    public static void main(String[] args) {
        GoodRefactoringDemo r = new GoodRefactoringDemo();
        int counter = 0;
        System.out.println(counter++);
        doSomething();

        System.out.println(Arrays.toString(new String[] {"cherry", "banana", "kiwi"}));
    }

    public static void doSomething() {}
}
