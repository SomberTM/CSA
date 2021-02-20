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
# __How do you accept a lambda within your own methods?__
Java provides a variety of [Functional Interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) that provide target types for lambda expressions and method references
#### Popular Functional Interfaces
- [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html)
  - Represents an operation that accepts a single input argument and returns no result.
- [BiConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html)
  - Represents an operation that accepts two input arguments and returns no result.
- [Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
  - Represents a function that accepts one argument and produces a result.
- [BiFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html)
  - Represents a function that accepts two arguments and produces a result.
