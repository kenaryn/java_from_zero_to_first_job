package vsca;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Gatherer;
import java.util.function.Consumer;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class UsingGatherers {
    public record ValueWithIndex<E>(E value, int index) {
        @Override
        public String toString() {
            return "%d: %s".formatted(index, value);
        }
    }

    static class Index {
        private int position = 0;

        public int getAndIncrement() {
            return position++;
        }
    }

    /*
     * Stateful sequential custom gatherer.
     * Carries the state from the processing item to the next one.
     */
    public static <T, R> Gatherer<? super T, ?, ValueWithIndex<R>>
    mapWithIndex(Function<T, R> mapper) {
        return Gatherer.ofSequential(Index::new,
                (index, element, downstream) ->
                        downstream.push(new ValueWithIndex<>(mapper.apply(element), index.getAndIncrement())));
    }

    public static void useMapWithIndex() {
        List.of("Aurèle", "Bob", "Tom", "Jerry", "Tyke")
            .parallelStream()
            .filter(name -> name.length() > 3)
            .gather(UsingGatherers.<String, String>mapWithIndex(String::toUpperCase))
            .forEachOrdered(System.out::println);
    }

    public static void usePeekInOrder() {
        var numbers = List.of(10, 11, 15, 12);

        numbers.parallelStream()
                .peek(System.out::println)
                .reduce(0, Integer::sum);

        System.out.println("----------");

        numbers.parallelStream()
                .gather(peekInOrder(System.out::println))  // executed sequentially to preserve the encountered order.
                .reduce(0, Integer::sum);
    }

    /*
     * Helper function to write a custom gatherer.
     *
     * @param element: element to process
     * @param consumer: abstract element's processing
     * @param downstream: reference to downstream step.
     * @return true or false to convey to the upstream step whether there are more data to send or not.
     */
    private static <T> boolean consumeAndPush(
            T element, @NotNull Consumer<T> consumer, @NotNull Gatherer.Downstream<? super T> downstream) {
        consumer.accept(element);
        return downstream.push(element);
    }

    /*
     * Stateless sequential customer gatherer.

     * Consumed types and produced types are the same.
     * The state machine to convey to the next item's processing is ignored.
     *
     * @param lambda expression as the Integrator is a functional interface.
     * @return a new stateless sequential gatherer.
     */
    private static <T> Gatherer<T, ?, T> peekInOrder(Consumer<T> consumer) {
        return Gatherer.ofSequential((_, element, downstream) ->
                consumeAndPush(element, consumer, downstream));
    }

    /*
     * Parallelizable stateless custom gatherer.
     */
    public static <T> Gatherer<? super T, ?, T> takeAnyOneMatching(
        Predicate<T> predicate) {
        return Gatherer.of((_, element, downstream) ->
                pushIfMatch(predicate, element, downstream));
    }

    public static void useTakeAnyOneMatching() {
        List.of(10, 11, 15, 12, 11, 44, 67, 83, 23, 12, 34, 12, 55)
            .parallelStream()
            .gather(UsingGatherers.<Integer>takeAnyOneMatching(e -> e > 25))
            .map(e -> e * 10)
            .forEach(System.out::println);
    }

    public static <T> boolean pushIfMatch(Predicate<T> predicate, T element, Gatherer.Downstream<? super T> downstream) {
        if (predicate.test(element)) {
            downstream.push(element);
            return false;
        }

        return true;
    }

    public record Person(String name, int age) {
        public int ageGroup() { return age / 10 * 10; }  // Tells us if a person belongs to a group of 10 year-old, 20 year-old, etc.
    }

    public static void useDistinctBy() {
        var people = List.of(new Person("Jill", 21), new Person("Jake", 8),
                new Person("Bill", 21), new Person("Nancy", 22), new Person("Mark", 9),
                new Person("Sarah", 18), new Person("Paul", 15), new Person("Theodor", 28));

        people.parallelStream()
                .gather(distinctByParallel(Person::ageGroup))
                .forEach(System.out::println);
    }

    /*
     * First pass: sequential stateful gatherer's implementation.
     */
    public static <T, C extends Comparable<C>> Gatherer<? super T, ?, T>
    distinctBy(Function<T, C> criterion) {
        return Gatherer.ofSequential(HashSet<C>::new, (state, element, downStream) ->
                !state.add(criterion.apply(element)) || downStream.push(element));
    }

    /*
     * Final pass: parallelizable stateful customer gatherer.
     */
    public static <T, C extends Comparable<C>> Gatherer<? super T, ?, T>
    distinctByParallel(Function<T, C> criterion) {
        return Gatherer.of(DistinctValues<T>::new,  // Supplier. Serves as an initialization state.
                (state, element, _) -> state.addIfDistinct(criterion, element),  // Integrator. Adds an element to the state.
                (state1, state2) -> state1.combineDistinct(criterion, state2),
                DistinctValues::pushEachValueDownstream);
    }

    static class DistinctValues<T> {
        private final Set<T> distinctElements = new LinkedHashSet<>();

        public <C extends Comparable<C>> boolean
        addIfDistinct(Function <T, C> criterion,  T element) {
            if(distinctElements.stream().noneMatch(existing ->
                    criterion.apply(existing).compareTo(criterion.apply(element)) == 0)) {
                distinctElements.add(element);
            }

            return true;
        }

        public <C extends Comparable<C>> DistinctValues<T>
        combineDistinct(Function<T, C> criterion, DistinctValues<T> toCombine) {
            for(var item: toCombine.distinctElements) {
                addIfDistinct(criterion, item);
            }

            return this;
        }

        public void pushEachValueDownstream(
                Gatherer.Downstream<? super T> downstream) {
            for(var element: distinctElements) {
                if(!downstream.push(element)) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        useMapWithIndex();
        useTakeAnyOneMatching();
        useDistinctBy();
    }
}
