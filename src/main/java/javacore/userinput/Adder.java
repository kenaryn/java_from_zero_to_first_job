package javacore.userinput;

public class Adder {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments were provided. Exiting now.");
            System.exit(1);
        }

        int sum = 0;
        for (var arg: args) {
            try {
                int n = Integer.parseInt(arg);
                sum += n;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid argument: " + ex.getMessage());
            }
        }
        System.out.println("Sum of all valid integers: " + sum);
    }
}
