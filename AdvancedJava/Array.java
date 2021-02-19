package AdvancedJava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Array<T> implements Iterable<T> {

    private ArrayList<T> ref;

    public Array() {
        this.ref = new ArrayList<T>();
    }

    @SafeVarargs
    public Array(T... values) {
        this.ref = new ArrayList<T>();
        for (T element : values)
            this.ref.add(element);
    }

    public Array(Iterable<T> iterable)
    {
        this.ref = new ArrayList<T>();
        for (T element : iterable) this.ref.add(element);
    }

    public Array(int initialCapacity) {
        this.ref = new ArrayList<T>(initialCapacity);
    }

    public Array(List<T> iterable) {
        this.ref = new ArrayList<T>(iterable);
    }

    public Array(Array<T> array) {
        this.ref = new ArrayList<T>();
        for (T element : array) this.ref.add(element);
    }

    public T get(int index) throws IndexOutOfBoundsException {
        return this.ref.get(index);
    }

    public void add(T value) {
        this.ref.add(value);
    }

    public void set(int index, T value) {
        this.ref.set(index, value);
    }

    public T delete(int index) throws IndexOutOfBoundsException {
        return this.ref.remove(index);
    }

    public boolean delete(T value) {
        return this.ref.remove(value);
    }

    public int size() {
        return this.ref.size();
    }

    public int indexOf(T value) { 
        return this.ref.indexOf(value); 
    }

    @FunctionalInterface
    static interface ConsumerIteration<T> {
        void accept(T value, Integer index, Array<T> array);
    }

    @FunctionalInterface
    static interface FunctionIteration<T, R> {
        R apply(T value, Integer index, Array<T> array);
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        for (int i = 0; i < this.size(); i++)
            consumer.accept(this.get(i));
    }

    public void forEachIteration(ConsumerIteration<T> consumer) {
        for (int i = 0; i < this.size(); i++)
            consumer.accept(this.get(i), i, this);
    }

    public T find(FunctionIteration<T, Boolean> function) throws NoSuchElementException {
        Optional<T> present = Optional.ofNullable(null);
        for (int i = 0; i < this.size(); i++)
            if (function.apply(this.get(i), i, this))
                present = Optional.of(this.get(i));
        return present.get();
    }

    public void addAll(Array<T> other) {
        for (int i = 0; i < other.size(); i++)
            this.add(other.get(i));
    }

    public void addAll(Iterable<T> other) {
        for (T element : other)
            this.add(element);
    }

    public Array<T> filter(FunctionIteration<T, Boolean> function) {
        Array<T> filtered = new Array<T>();
        for (int i = 0; i < this.size(); i++)
            if (function.apply(this.get(i), i, this))
                filtered.add(this.get(i));
        return filtered;
    }

    public boolean some(FunctionIteration<T, Boolean> function) {
        for (int i = 0; i < this.size(); i++) 
            if (function.apply(this.get(i), i, this))
                return true;
        
        return false;
    }

    public boolean every(FunctionIteration<T, Boolean> function) {
        for (int i = 0; i < this.size(); i++)
            if (!function.apply(this.get(i), i, this))
                return false;

        return true;
    }

    /**
     * Collection of array sorting methods. Use the double colon operator when referencing these methods within the sort function.
     * 
     * <p>
     * <h3>Sort Example: </h3>
     * <pre>{@code
     *Array<Integer> numbers = new Array<Integer>(1, 5, 7, 2, 4, 6, 9);
     *System.out.println(numbers);
     *numbers.sort(Array.Sort::Ascending);
     *System.out.println(numbers);
     * }</pre>
     * </p>
     * 
     * @param Ascending - Sorts {@link Number} or {@link CharSequence} arrays in ascending order. For {@link CharSequence} the default {@link Comparable#compareTo} method is used
     * @param Descending - Sorts {@link Number} or {@link CharSequence} arrays in descending order. For {@link CharSequence} the default {@link Comparable#compareTo} method is used
     * 
     * 
     */
    static abstract class Sort {

        public static <T extends Number> int Ascending(T a, T b) { return CompareNumbers(a, b); }
        public static <T extends Number> int Descending(T a, T b) { return CompareNumbers(b, a); }

        public static <T extends CharSequence> int Ascending(T a, T b) { return CompareStrings(a, b); }
        public static <T extends CharSequence> int Descending(T a, T b) { return CompareStrings(b, a); }

        public static <T extends CharSequence> int CompareStrings(T a, T b) { return a.toString().toLowerCase().compareTo(b.toString().toLowerCase()); }
        public static <T extends Number> int CompareNumbers(T a, T b) { return a.intValue() - b.intValue(); }
        public static <T extends Comparable<T>> int Compare(T a, T b) { return a.compareTo(b); }
    }

    @FunctionalInterface
    static interface Sorter<T, U, R> {
        R apply(T t, U u);
    }

    /**
     * Sorts this array based off of the default compareTo method of each object
     * @return This array sorted in place.
     */
    @SuppressWarnings("unchecked")
    public Array<T> sort() {
        this.ref.sort((a, b) -> {  
            return ((Comparable<T>)(a)).compareTo(b);
        });
        return this;
    }

    /**
     * Sorts an array based off of the given comparator
     * @param comparator - A comparator function that returns an integer which represents the result of comparing two objects
     * @return This array sorted in place.
     */
    public Array<T> sort(Sorter<T, T, Integer> comparator) {
        this.ref.sort((a, b) -> {
            return comparator.apply(a, b);
        });
        return this;
    }

    public Array<T> sort(Function<Array<T>, Array<T>> sorter) {
        Array<T> sorted = sorter.apply(this);
        for (int i = 0; i < sorted.size(); i++)
            this.set(i, sorted.get(i));
        return this;
    }

    /**
     * Copies this array then sorts its
     * @return Sorted copy of this array
     */
    public Array<T> osort() {
        return this.copy().sort();
    }

    /**
     * Copies this array then sorts based off of the given comparator
     * @param comparator - A comparator function that returns an integer which represents the result of comparing two objects
     * @return Sorted copy of this array
     */
    public Array<T> osort(Sorter<T, T, Integer> comparator) {
        return this.copy().sort(comparator);
    }

    /**
     * Tests equality for each element of {@code this} with the result of {@code this.osort()}
     * @return Whether this array is sorted or not
     */
    public boolean isSorted() {
        Array<T> sorted = this.osort();
        return this.equals(sorted);
    }
    
    /**
     * Tests equality for each element of {@code this} with the result of {@code this.osort(sorter)}
     * @param sorter - The function to osort this array with
     * @return Whether this array is sorted based off of the given sort function
     */
    public boolean isSorted(Sorter<T, T, Integer> sorter) {
        Array<T> sorted = this.osort(sorter);
        return this.equals(sorted);
    }

    /**
     * Returns a new array containg the results of applying each element of the array to the given function 
     * @param function - The function to call on each element of the array 
     */
    public <R extends Comparable<R>> Array<R> map(FunctionIteration<T, R> function) {
        Array<R> out = new Array<R>();
        for (int i = 0; i < this.size(); i++) 
            out.add(function.apply(this.get(i), i, this));
        return out;
    }

    /**
     * Calls a given function on each element of the array and updates said element to the return value of the function
     * @param function - The function to call on each element of the array
     */
    public void update(FunctionIteration<T, T> function) {
        for (int i = 0; i < this.size(); i++)
            this.ref.set(i, function.apply(this.get(i), i, this));
    }

    /**
     * @return A shallow copy of this array
     */
    public Array<T> copy() { return new Array<T>(this); }

    /**
     * Returns an array containing elements from begin-end of this array
     * @param begin - The index to start from  
     * @param end - The index to end at
     * @return 
     * @throws IndexOutOfBoundsException Thrown if begin or end index is outside of the array bounds
     */
    public Array<T> from(int begin, int end) throws IndexOutOfBoundsException {
        return this.slice(begin, end, false);
    }

    /**
     * Returns an array containing elements from begin to the end of this array
     * @param begin - The index to start from  
     * @return 
     * @throws IndexOutOfBoundsException Thrown if begin or end index is outside of the array bounds
     */
    public Array<T> from(int begin) throws IndexOutOfBoundsException {
        return this.from(begin, this.size());
    }

    /**
     * Joins an array with the given seperator.
     * @param seperator - A string to seperate each element of the array by
     * @return A string that contains the elements of the array joined by the given seperator.
     * @apiNote Calls toString() on each element of the array
     */
    public String join(String seperator) {
        return this.join(seperator, true);
    }    

    /**
     * Joins an array with the given seperator.
     * @param seperator - A string to seperate each element of the array by
     * @param trim - Whether to trim / remove leading and trailing whitespace
     * @return A string that contains the elements of the array joined by the given seperator.
     * @apiNote Calls toString() on each element of the array
     */
    public String join(String seperator, boolean trim) {
        String out = "";
        for (int i = 0, s = this.size(); i < s; i++) {
            out += this.get(i).toString();
            if (i < s - 1) out += seperator;
        }
        return trim ? out.trim() : out;
    }    

    /**
     * Returns an array of elements from begin index to end index.
     * @param begin - The index of where to start splicing
     * @param end - The index of where to end the splicing
     * @return An array of spliced elements from the begin index to end index
     * @throws IndexOutOfBoundsException Thrown if begin or end index is outside of the array bounds
     */
    public Array<T> slice(int begin, int end) throws IndexOutOfBoundsException {
        return this.slice(begin, end, true);
    }

    /**
     * Returns an array of elements from begin index to end index.
     * If remove is true then the elements will be removed from the array, otherwise they won't be.
     * @param begin - The index of where to start splicing
     * @param end - The index of where to end the splicing
     * @param remove - Whether to remove the spliced elements or not
     * @return An array of spliced elements from the begin index to end index
     * @throws IndexOutOfBoundsException Thrown if begin or end index is outside of the array bounds
     */
    public Array<T> slice(int begin, int end, boolean remove) throws IndexOutOfBoundsException {
        if (begin >= this.size() || end >= this.size())
            throw new IndexOutOfBoundsException();

        Array<T> out = new Array<T>();
        for (int i = begin; i < end; i++)
            out.add(this.get(i));

        if (remove)
            for (T element : out) this.ref.remove(element);
        
        return out;
    }

    /**
     * Swaps to elements given two indices
     * @param firstIndex - The index of the first element to swap
     * @param secondIndex - The index of the second element to swap
     * @throws IndexOutOfBoundsException Thrown if either index is outside the array size or less than 0
     */
    public void swap(int firstIndex, int secondIndex) throws IndexOutOfBoundsException {
        if (firstIndex < 0 || firstIndex >= this.size())
            throw new IndexOutOfBoundsException(firstIndex);
        else if (secondIndex < 0 || secondIndex >= this.size())
            throw new IndexOutOfBoundsException(secondIndex);

        T temp = this.get(firstIndex);
        this.set(firstIndex, this.get(secondIndex));
        this.set(secondIndex, temp);
    }

    static class ConditonalReduceOperation<T> {
        Boolean reduce(T next, T current) { return null; }  
    }

    @FunctionalInterface
    static interface ConditionalReducer<T> {
        Boolean apply(T next, T current);
    }

    public T reduceCondition(ConditionalReducer<T> conditional) {
        T current = this.get(0);
        T next;
        for (int i = 1; i < this.size(); i++) {
            next = this.get(i);
            if (conditional.apply(next, current))
                current = next;
        }
        return current;
    }

    public T reduceCondition(ConditonalReduceOperation<T> operation) {
        return this.reduceCondition(operation::reduce);
    }

    static class ReduceOperation<T> {
        T reduce(T accumulator, T current) { return null; }
    }

    @FunctionalInterface
    static interface Reducer<T> {
        T apply(T a, T b);
    }

    public T reduce(Reducer<T> reducer) {
        T accumulator = this.get(0);
        T next;
        for (int i = 1; i < this.size(); i++) {
            next = this.get(i);
            accumulator = reducer.apply(accumulator, next);
        }
        return accumulator;
    }

    public T reduce(ReduceOperation<T> operation) {
        return this.reduce(operation::reduce);
    }

    /**
     * Does this array have this value in it
     * @param value - Element to check if its in the list
     * @return true if the element is in the array, false otherwise.
     */
    public boolean includes(T value) {
        return this.ref.contains(value);
    }

    /**
     * Tests if two arrays are equal. For two arrays to be equal the value at any given index in this array must match the value in the other array at the given index
     * @param other - The array to test equality with
     * @return Whether the two arrays are equal
     */
    public boolean equals(Array<T> other) {
        if (this.size() != other.size()) return false;
        for (int i = 0; i < this.size(); i++)
            if (!this.get(i).equals(other.get(i)))
                return false;
        return true;
    }

    /**
     * Tests if any two elements are equal
     * @apiNote Uses the {@code equals} method for comparison
     */
    public boolean hasDuplicates() {
        for (int i = 0; i < this.size() - 1; i++) {
            T curr = this.get(i);
            if (curr.equals(this.get(i+1)))
                return true;
        }
        return false;
    }

    /**
     * @return The iterator for this array 
     */
    public Iterator<T> iterator() {
        return this.ref.iterator();
    }

    @Override
    public String toString() {
        return String.format("[len:%d][%s]", this.size(), this.map((v, i, a) -> String.format("%s", v != null ? v.toString() : v)).join(", "));
    }

    /**
     * Filters out all null elements in this array
     * @return An array of all non-null elements in this array
     */
    public Array<T> asNonNull() {
        return this.filter((v, i, a) -> v != null);
    }

    public static <T> Array<T> of(int length) {
        Array<T> array = new Array<T>(length);
        for (int i = 0; i < length; i++)
            array.add(null);
        return array;
    }

    public static <T> Array<T> of(int length, Function<Integer, T> function) {
        Array<T> array = new Array<T>(length);
        for (int i = 0; i < length; i++)
            array.add(function.apply(i));
        return array;
    }

    public static <T> Array<T> asNonNull(Array<T> array) {
        return array.asNonNull();
    }

    public static Array<Integer> from(int[] array) {
        Array<Integer> out = new Array<Integer>(array.length);
        for (int i : array) out.add(i);
        return out;
    }

    public static Array<Long> from(long[] array) {
        Array<Long> out = new Array<Long>(array.length);
        for (long i : array) out.add(i);
        return out;
    }

    public static Array<Double> from(double[] array) {
        Array<Double> out = new Array<Double>(array.length);
        for (double i : array) out.add(i);
        return out;
    }

    public static Array<Float> from(float[] array) {
        Array<Float> out = new Array<Float>(array.length);
        for (float i : array) out.add(i);
        return out;
    }

    public static Array<Short> from(short[] array) {
        Array<Short> out = new Array<Short>(array.length);
        for (short i : array) out.add(i);
        return out;
    }

    public static Array<String> from(String[] array) {
        Array<String> out = new Array<String>(array.length);
        for (String i : array) out.add(i);
        return out;
    }

    public static Array<Boolean> from(boolean[] array) {
        Array<Boolean> out = new Array<Boolean>(array.length);
        for (boolean i : array) out.add(i);
        return out;
    }

    public static Array<Character> from(char[] array) {
        Array<Character> out = new Array<Character>(array.length);
        for (char i : array) out.add(i);
        return out;
    }

    public static Array<Byte> from(byte[] array) {
        Array<Byte> out = new Array<Byte>(array.length);
        for (byte i : array) out.add(i);
        return out;
    }

}