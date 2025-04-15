package vsca;

import java.util.List;
import java.util.stream.Gatherer;
import java.util.function.Function;

public class CustomMap {
    public static Gatherer<Integer, ?, Integer> redundantMap(Function<Integer, Integer> mapper) {
        return Gatherer.of((_, element, downstream) ->
                downstream.push(mapper.apply(element)));
    }

    public static void main(String[] args) {
        List.of(1, 2, 3)
            .stream()
            .gather(redundantMap(e -> e * 2))
            .forEach(System.out::println);
    }
}
