package AdvancedJava;

public class Program {

    public static void main(String[] args) {
        try {
            Class<?> t = Class.forName("AdvancedJava.Lambdas.Example");
            System.out.println(t);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Example.test(AdvancedJava.Lambdas.Example.class);
    }

}
