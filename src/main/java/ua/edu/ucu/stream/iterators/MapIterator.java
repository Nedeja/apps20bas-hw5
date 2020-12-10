package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private IntUnaryOperator mapper;
    private boolean used = false;

    public MapIterator(Iterator<Integer> iterator, IntUnaryOperator mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        if (used) {
            throw new RuntimeException("Stream is used");
        }
        boolean res = this.iterator.hasNext();
        if (!res) {
            used = true;
        }
        return res;
    }

    @Override
    public Integer next() {
        return mapper.apply(iterator.next());
    }
}
