package AdvancedJava;

public abstract class Example {
    
    public abstract void run();

    public enum Testable {
        LAMBDAS,
        ANONYMOUS_CLASSES,
        FUNCTIONAL_INTERFACES,
        GENERICS,
        METHOD_REFERENCES,
        VARIADIC_ARGUMENTS
    }

    public static <T extends Example> void test(Class<T> _class) {
        try {
            T example = _class.getDeclaredConstructor().newInstance();
            example.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Example> void test(String className) {
        try {
            Class<T> tester = (Class<T>)Class.forName(className);
            T instance = tester.getDeclaredConstructor().newInstance();
            instance.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T extends Example> void test(Testable testable) {
        String base = "AdvancedJava.%s.Example";
        switch(testable) {
            case LAMBDAS:
                Example.test(String.format(base, "Lambdas"));
                break;
            case ANONYMOUS_CLASSES:
                Example.test(String.format(base, "AnonymousClasses"));
                break;
            case FUNCTIONAL_INTERFACES:
                Example.test(String.format(base, "FunctionalInterfaces"));
                break;
            case GENERICS:
                Example.test(String.format(base, "Generics"));
                break;
            case METHOD_REFERENCES:
                Example.test(String.format(base, "MethodReferences"));
                break;
            case VARIADIC_ARGUMENTS:
                Example.test(String.format(base, "VariadicArguments"));
                break;
            default:
                break;
        }
    }

}
