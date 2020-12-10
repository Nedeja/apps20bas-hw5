package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private IntPredicate predicate;
    private int next;
    private boolean hasNext;
    private boolean used = false;

    public FilterIterator(Iterator<Integer> iterator, IntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        if (iterator.hasNext()) {
            hasNext = true;
            next = iterator.next();
            while (!predicate.test(next) && iterator.hasNext()) {
                next = iterator.next();
            }
        } else {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        if (used) {
            throw new RuntimeException("Stream is used");
        }
        boolean res = hasNext && predicate.test(next);
        if (!res) {
            used = false;
        }
        return res;
    }

    @Override
    public Integer next() {
        int current = next;
        if (iterator.hasNext()) {
            next = iterator.next();
            while (!predicate.test(next) && iterator.hasNext()) {
                next = iterator.next();
            }
        } else {
            hasNext = false;
        }
        return current;
    }
}
