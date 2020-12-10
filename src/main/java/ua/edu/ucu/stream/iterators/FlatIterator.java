package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;

import java.util.Iterator;

public class FlatIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private IntToIntStreamFunction func;
    private int[] arr;
    private int i = 0;
    private int next;
    private boolean used = false;

    public FlatIterator(Iterator<Integer> prevIterator, IntToIntStreamFunction func) {
        if (used) {
            throw new RuntimeException("Stream is used");
        }
        prevIterator.hasNext();
        this.iterator = prevIterator;
        this.func = func;
        arr = func.applyAsIntStream(prevIterator.next()).toArray();
    }

    @Override
    public boolean hasNext() {
        boolean res = i < arr.length || this.iterator.hasNext();
        if (!res) {
            used = true;
        }
        return res;
    }

    @Override
    public Integer next() {
        if (i < arr.length) {
            next = arr[i];
            i++;
        } else {
            arr = func.applyAsIntStream(iterator.next()).toArray();
            i = 0;
            next = arr[i];
            i++;
        }
        return next;
    }
}