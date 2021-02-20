package AdvancedJava.Lambdas;

import java.util.function.*;

public class Example extends AdvancedJava.Example {

    private void forEach(int[] numbers, Consumer<Integer> consumer) {
        for (int i : numbers)
            consumer.accept(i);
    }

    @Override
    public void run() {
        int[] numbers = { 1, 5, 6, 2, 3, 4, 5, 9, 29, 12, 10 };
        this.forEach(numbers, number -> {
            
        });
    }

}
