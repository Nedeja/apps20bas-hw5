package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void setUp() {
        intStream = AsIntStream.of(1, 2, 3 ,4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void averageEmpty() {
        IntStream newStream = AsIntStream.of();
        Double a = newStream.average();
    }

    @Test
    public void average() {
        Double a = intStream.average();
        assertEquals(2.5, a, 0.0);
    }

    @Test (expected = RuntimeException.class)
    public void averageIfTerminal() {
        Double a = intStream.average();
        assertEquals(2.5, a, 0.0);
        int[] s = intStream.toArray();
    }

    @Test (expected = IllegalArgumentException.class)
    public void maxEmpty() {
        IntStream newStream = AsIntStream.of();
        int m = newStream.max();
    }

    @Test
    public void max() {
        int m = intStream.max();
        assertEquals(4, m);
    }

    @Test (expected = RuntimeException.class)
    public void maxIfTerminal() {
        int m = intStream.max();
        assertEquals(4, m);
        int[] s = intStream.toArray();
    }

    @Test (expected = IllegalArgumentException.class)
    public void minEmpty() {
        IntStream newStream = AsIntStream.of();
        int m = newStream.min();
    }

    @Test
    public void min() {
        int m = intStream.min();
        assertEquals(1, m);
    }

    @Test (expected = RuntimeException.class)
    public void minIfTerminal() {
        int m = intStream.min();
        assertEquals(1, m);
        int[] s = intStream.toArray();
    }

    @Test
    public void countEmpty() {
        IntStream newStream = AsIntStream.of();
        long n = newStream.count();
        assertEquals(0, n);
    }

    @Test
    public void count() {
        long n = intStream.count();
        assertEquals(4, n);
    }

    @Test (expected = RuntimeException.class)
    public void countIfTerminal() {
        long n = intStream.count();
        assertEquals(4, n);
        int[] s = intStream.toArray();
    }

    @Test
    public void countAfterFilter() {
        long n = intStream.filter(x -> x > 2).count();
        assertEquals(2, n);

    }

    @Test (expected = IllegalArgumentException.class)
    public void sumEmpty() {
        IntStream newStream = AsIntStream.of();
        int s = newStream.sum();
        assertEquals(0, s);
    }

    @Test
    public void sum() {
        int s = intStream.sum();
        assertEquals(10, s);
    }

    @Test (expected = RuntimeException.class)
    public void sumIfTerminal() {
        int s = intStream.sum();
        assertEquals(10, s);
        int ss = intStream.sum();
    }

    @Test (expected = RuntimeException.class)
    public void sumAfterMap() {
        int s = intStream.map(x -> x * x).sum();
        assertEquals(1 + 4 + 9 + 16, s);
        int ss = intStream.sum();
    }

    @Test
    public void reduce() {
        int res = intStream.reduce(1, (mul, x) -> mul *= x);
        assertEquals(24, res);
    }

    @Test
    public void toArray() {
        assertArrayEquals(new int[]{1,2,3,4}, intStream.toArray());
    }

    @Test
    public void toArrayEmpty() {
        IntStream newStream = AsIntStream.of();
        assertArrayEquals(new int[]{}, newStream.toArray());
    }

    @Test(expected = RuntimeException.class)
    public void testFilterMapReduceTerminal() {
        int res = intStream.filter(x -> x > -1).map(x -> x).reduce(0, (l, x) -> l = x);
        assertEquals(4, res);
        int[] a = intStream.toArray();

    }


}
