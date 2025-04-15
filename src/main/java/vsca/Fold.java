package vsca;

import java.util.stream.Stream;
import java.util.stream.Gatherers;

public class Fold {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6)
            .filter(e -> e % 2 != 0)
            .gather(Gatherers.scan(() -> 0, Integer::sum))  // 1, 3, 5
//            .map(e -> e * 10)
            .forEach(System.out::println);
    }
}
