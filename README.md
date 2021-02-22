# CSA
```java
import java.lang.FunctionalInterface;

public class Program {

    interface IDisposable<T> {
        void dispose();
    }

    @FunctionalInterface
    interface Using<T extends IDisposable<T>> {
      void accept(T disposable);
    }

    public static void main(String[] args) {
    
    }
    
    public static <T> void using(IDisposable<T> disposable, Using<T> function) {
        function.accept(disposable);
        disposable.dispose();
    }

}
```
