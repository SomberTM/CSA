import java.lang.FunctionalInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    static interface IDisposable {
        void dispose();
    }

    @FunctionalInterface
    static interface Using {
      void voids();
    }

    @FunctionalInterface
    static interface AcceptUsing<T extends IDisposable> {
      void accept(T disposable);
    }

    public static <T extends IDisposable> void using(T disposable, Using function) {
        function.voids();
        disposable.dispose();
    }

    public static <T extends IDisposable> void using(T disposable, AcceptUsing<T> function) {
        function.accept(disposable);
        disposable.dispose();
    }

    public static class DisposableArray<T> implements IDisposable {

        List<T> list = new ArrayList<T>();

        @SafeVarargs
        public DisposableArray(T... values) {
            for (T value : values)
                list.add(value);
        }

        public void dispose() {
            this.list = null;
        }

        @Override
        public String toString() { return this.list != null ? Arrays.toString(this.list.toArray()) : "Disposed"; }
    }

    public static void main(String[] args) {
        DisposableArray<Integer> disposableArray = new DisposableArray<>(5, 6, 1, 2, 10);

        using(disposableArray, () -> {
            System.out.println(disposableArray);
        });

        System.out.println(disposableArray);
    }

}
