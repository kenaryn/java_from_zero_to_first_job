package vsca;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

/**
 *
 * Multiples' computation.
 *
 * @author Aurélien_Plazzotta
 * @version alpha1
 *
 */
public class Euler1 {
    /**
     * Find the sum of all the multiples of 3 and 5 below 1000.
     *
     * @deprecated this doc comment will be superseeded by yet-to-be written unit tests.
     * @see "https://pragprog.com/titles/utj3/pragmatic-unit-testing-in-java-with-junit-third-edition/"
     */
    public static void findMultiples() {
        IntStream.range(1, 1000)
                .filter(e -> e % 3 == 0 || e % 5 == 0)
                .boxed() // Converts `IntStream` elements into `Stream<Integer>` for downstream step.
                .gather(Gatherers.fold(() -> 0, Integer::sum))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        findMultiples();
    }
}
