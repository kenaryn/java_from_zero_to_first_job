package vsca;

import java.util.stream.Stream;
import java.util.stream.Gatherers;

public class WindowsGatherers {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
            .gather(Gatherers.windowFixed(3))
            .forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5, 6, 7)
            .gather(Gatherers.windowSliding(3))
            .forEach(System.out::println);
    }
}
