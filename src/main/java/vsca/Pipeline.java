package vsca;

import java.util.stream.Stream;

public class Pipeline {
    public static void main(String[] args) {
        var result = Stream.of(1, 2, 5, 4, 3, 6)
                .takeWhile(e -> e != 3)
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .sum();

        System.out.println(result);
    }
}
