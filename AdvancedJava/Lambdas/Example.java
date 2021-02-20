package AdvancedJava.Lambdas;

import java.util.Arrays;
import java.util.function.*;

public class Example extends AdvancedJava.Example {

    private int[] numbers = { 5, 1, 2, 4, 6, 7, 10, 9, 18 };

    private void arrayForEach(int[] numbers, Consumer<Integer> consumer) {
        for (int i : numbers)
            consumer.accept(i);
    }

    public void arrayForEach(int[] numbers, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < numbers.length; i++)
            consumer.accept(numbers[i], i);
    }

    public boolean arrayEvery(int[] numbers, Function<Integer, Boolean> function) {
        boolean every = true;
        for (int number : numbers)
            if (!function.apply(number))
                every = false;
        return every;
    }

    private Integer[] createArray(int length, BiFunction<Integer, Integer[], Integer> function) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++)
            array[i] = function.apply(i, array);
        return array;
    }

    @Override
    public void run() {

        arrayForEach(this.numbers, number -> System.out.println(number));
        
        // When accepting 2 or more values with a lambda they must be captured within () but are optional for 1 value
        arrayForEach(numbers, (number, index) -> System.out.println(number + " occurs at index " + index));

        System.out.println("Are all numbers greater than 5? " + arrayEvery(numbers, number -> { 
            return number > 5; 
        }));

        Integer[] integers = createArray(10, (index, array) -> {
            if (index > 0)
                return index * array[index-1];
            else
                return 1;
        });

        System.out.println(Arrays.toString(integers));
    }

}
