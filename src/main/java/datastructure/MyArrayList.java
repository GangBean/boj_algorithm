package datastructure;

import java.util.Arrays;

public class MyArrayList<T> {
    private static final int INITIAL_SIZE = 8;
    private Object[] values;
    private int size;

    public MyArrayList() {
        this.values = new Object[INITIAL_SIZE];
    }

    public void add(T value) {
        if (size + 1 == values.length) {
            resize();
        }
        values[size++] = value;
    }

    public void remove(T value) {

    }

    private void resize() {
        this.values = Arrays.copyOf(values, values.length * 2);
    }

    @SuppressWarnings("unchecked")
    public T get(int idx) {
        validateIdx(idx);
        return (T) values[idx];
    }

    private void validateIdx(int idx) {
        if (idx >= size) throw new RuntimeException();
    }

}
