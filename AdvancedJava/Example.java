package AdvancedJava;

public abstract class Example {
    
    public abstract void run();

    public static <T extends Example> void test(Class<T> _class) {
        try {
            T example = _class.getDeclaredConstructor().newInstance();
            example.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
