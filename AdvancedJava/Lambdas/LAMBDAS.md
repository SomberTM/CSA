# __What are lambdas in java?__
Lambdas are expressions, or anonymous functions, that are similar to methods but they do not need a name or implementation within a class body. 
Instead lambdas are defined within a method as a paramater.
#### Basic lambda syntax & functionality
```java 
// The arrow (->) syntax is used to denote the use of a lambda expression
method(value -> { System.out.println(value); });

// Lambdas don't have to define a function body although it does come with drawbacks.
// These drawbacks being that you can't use any special keywords such as 'if' or 'return' but you can still call other methods.
method(value -> anotherMethod(value)); '✔'
method(value -> if (value % 2 == 0) return true); '❌'

// Lambdas without function bodies will also automatically return what is contained within them.
method(value -> value + 5);
```
# __How do you accept a lambda within your own method?__
Java provides a variety of [Functional Interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) that provide target types for lambda expressions and method references
#### Most common Functional Interfaces
- [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html)
  - Represents an operation that accepts a single input argument and returns no result.
- [BiConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html)
  - Represents an operation that accepts two input arguments and returns no result.
- [Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
  - Represents a function that accepts one argument and produces a result.
- [BiFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html)
  - Represents a function that accepts two arguments and produces a result.
#### Method Implementation
```java
public void arrayForEach(int[] numbers, Consumer<Integer> consumer) {
    for (int number : numbers)
        consumer.accept(number);
}
// The lambda should take in one argument and return void
arrayForEach(numbers, (number) -> System.out.println(number));
```
```java
public void arrayForEach(int[] numbers, BiConsumer<Integer, Integer> consumer) {
    for (int i = 0; i < numbers.length; i++)
        consumer.accept(numbers[i], i);
}
// The lambda should take in two arguments and return void
arrayForEach(numbers, (number, index) -> System.out.println(number + " occurs at index " + index));
```
```java
// Checks if every element in the array matches some condition specified within the lambda
public boolean arrayEvery(int[] numbers, Function<Integer, Boolean> function) {
    boolean every = true;
    for (int number : numbers)
        if (!function.apply(number))
            every = false;
    return every;
}
// The lambda should take in one argument and return a boolean
arrayEvery(numbers, (number) -> { 
    return number > 5; 
});
// If all numbers in the given array are > 5 then arrayEvery will return true
```
```java
public Integer[] createArray(int length, BiFunction<Integer, Integer[], Integer> function) {
    Integer[] array = new Integer[length];
    for (int i = 0; i < length; i++)
        array[i] = function.apply(i, array);
    return array;
}
// The lambda should take in two arguments, an integer and an integer array, and return an integer
createArray(10, (index, array) -> {
    if (index > 0)
        return index * array[index-1];
    else
        return 1;
});
```
###### Links
- [W3Schools](https://www.w3schools.com/java/java_lambda.asp#:~:text=Lambda%20Expressions%20were%20added%20in,the%20body%20of%20a%20method.)
- [Java Example](/AdvancedJava/Lambdas/Example.java)