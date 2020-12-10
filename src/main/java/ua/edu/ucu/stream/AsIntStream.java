package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.stream.iterators.FilterIterator;
import ua.edu.ucu.stream.iterators.FlatIterator;
import ua.edu.ucu.stream.iterators.MapIterator;
import ua.edu.ucu.stream.iterators.StreamIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    private Iterator iterator;

    private AsIntStream(int[] values) {
        this.iterator = new StreamIterator(values);
    }

    private AsIntStream(Iterator iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private Iterable<Integer> toIterable() {
        return ()->iterator;
    }

    @Override
    public Double average() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        int len = 0;
        for (int i: toIterable()) {
            sum += i;
            len += 1;
        }
        return (double) sum/len;
    }

    @Override
    public Integer max() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        int max;
        max = Integer.MIN_VALUE;
        for (int i : toIterable()) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public int min() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        int min = Integer.MAX_VALUE;
        for (int i : toIterable()) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    @Override
    public long count() {
        long len = 0;
        for (int i : toIterable()) {
            len += 1;
        }
        return len;
    }

    @Override
    public Integer sum() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int i : toIterable()) {
            sum += i;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : toIterable()) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatIterator(this.iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        int res = identity;
        for (int i : toIterable()) {
            res = op.apply(res, i);
        }
        return res;

    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i : toIterable()) {
            newList.add(i);
        }
        int[] newArray = new int[newList.size()];
        for (int j = 0; j < newList.size(); j++) {
            newArray[j] = newList.get(j);
        }
        return newArray;
    }
}
