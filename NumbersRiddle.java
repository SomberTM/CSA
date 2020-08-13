import java.util.ArrayList;
import java.util.Arrays;

public class NumbersRiddle {

    /**
     * The 'magic' function that takes an int and always returns 3
     * 
     * @param start
     * @return 3
     */
    public static int magic(int start) {
        // Store original value
        int temp = start;
        int value = start * 2;
        System.out.println("Doubled: " + value);
        value += 6;
        System.out.println("Added 6: " + value);
        value /= 2;
        System.out.println("Divided by 2: " + value);
        value -= temp;
        System.out.println("Subtracted original: " + value);
        return value;
    }

    /**
     * Runs the magic operation on each of the test cases supplied and prints the results
     * 
     * @param positiveInteger Any positive integer
     * @param negativeInteger Any negative integer
     * @param zero the number 0
     * @param one the number 1
     * @param positiveDouble Any positive double
     * @param negativeDouble Any negative double
     */
    public static void testCases(int positiveInteger, int negativeInteger, int zero, int one, double positiveDouble, double negativeDouble) {
        // Convert invalid arguments (if any)
        if (positiveInteger < 0) positiveInteger = Math.abs(positiveInteger);
        if (negativeInteger > 0) negativeInteger -= (negativeInteger * 2);
        if (zero != 0) zero = 0;
        if (one != 1) one = 1;
        if (positiveDouble < 0.0) positiveDouble = Math.abs(positiveDouble);
        if (negativeDouble > 0.0) negativeDouble -= (negativeDouble * 2);
        
        // Store values in array with type Object instead of casting each to int and storing in Integer array
        // This is done so we can print out the original value as well as the magic done
        ArrayList<Object> originals = new ArrayList<Object>(Arrays.asList(positiveInteger, negativeInteger, zero, one, positiveDouble, negativeDouble));

        for (Object i : originals) {        
            //If double floor it so we can cast to int. else just cast to int.
            System.out.println("Original: " + i.toString());
            magic(i instanceof Double ? (int)Math.floor((double)i) : (int)i);
            System.out.println("---------------");
        }
    }

    public static void main(String[] args) {
        testCases(123, -5645620, 0, 1, 10.653, -26.58);
    }

}
