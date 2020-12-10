package ua.edu.ucu.stream.iterators;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private int[] values;
    private int iterable;
    private boolean used = false;

    public StreamIterator(int[] values) {
        this.values = values;
        this.iterable = 0;
    }

    @Override
    public boolean hasNext() {
        if (used) {
            throw new RuntimeException("Stream is used");
        }
        boolean res = iterable < values.length;
        if (!res) {
            used = true;
        }
        return res;
    }

    @Override
    public Integer next() {
        iterable += 1;
        return values[iterable - 1];
    }
}
