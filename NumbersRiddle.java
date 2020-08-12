import java.util.ArrayList;
import java.util.Arrays;

public class NumbersRiddle {

    public static int magic(int start) {
        // Store original value
        int temp = start;
        int value = start * 2;
        value += 6;
        value /= 2;
        value -= temp;
        return value | 3;
    }

    public static void supply(int positiveInteger, int negativeInteger, int zero, int one, double positiveDouble, double negativeDouble) {
        // Convert invalid arguments (if any)
        if (positiveInteger < 0) positiveInteger = Math.abs(positiveInteger);
        if (negativeInteger > 0) negativeInteger -= (negativeInteger * 2);
        if (zero != 0) zero = 0;
        if (one != 1) one = 1;
        if (positiveDouble < 0.0) positiveDouble = Math.abs(positiveDouble);
        if (negativeDouble > 0.0) negativeDouble -= (negativeDouble * 2);
        
        ArrayList<Object> originals = new ArrayList<Object>(Arrays.asList(positiveInteger, negativeInteger, zero, one, positiveDouble, negativeDouble));

        for (Object i : originals) {        
            /**
             * If one of the values in the array is a double we need to get it to a suitable place so that it can be casted to an integer (otherwise we get error) 
             * 
             */
             int value = magic(i instanceof Double ? (int)Math.floor((double)i) : (int)i);
            System.out.println("Original: " + i.toString() + " | Magic: " + value);
        }
    }

    public static void main(String[] args) {
        supply(-10, 5645620, 5, 12, -10.6, 2.5);
    }

}