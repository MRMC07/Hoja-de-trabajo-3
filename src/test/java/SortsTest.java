
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SortsTest {

    private Integer[] randomArray(int n, long seed) {
        Random r = new Random(seed);
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(100000);
        return a;
    }

    private void assertSameAsJavaSort(Integer[] original, Sorter sorter) {
        Integer[] expected = Arrays.copyOf(original, original.length);
        Arrays.sort(expected);

        Integer[] actual = Arrays.copyOf(original, original.length);
        sorter.sort(actual);

        assertArrayEquals(expected, actual);
        assertTrue(Sorts.isSorted(actual));
    }

    @FunctionalInterface
    interface Sorter { void sort(Integer[] a); }

    @Test
    void testGnome() {
        assertSameAsJavaSort(randomArray(200, 1), Sorts::gnomeSort);
    }

    @Test
    void testMerge() {
        assertSameAsJavaSort(randomArray(200, 2), Sorts::mergeSort);
    }

    @Test
    void testQuick() {
        assertSameAsJavaSort(randomArray(200, 3), Sorts::quickSort);
    }

    @Test
    void testHeap() {
        assertSameAsJavaSort(randomArray(200, 4), Sorts::heapSort);
    }

    @Test
    void testRadix() {
        Integer[] a = randomArray(200, 5);
        Integer[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Sorts.radixSort(a);

        assertArrayEquals(expected, a);
        assertTrue(Sorts.isSorted(a));
    }

    @Test
    void testEdgeCases() {
        Integer[] empty = new Integer[0];
        Sorts.mergeSort(empty);
        assertTrue(Sorts.isSorted(empty));

        Integer[] one = {7};
        Sorts.quickSort(one);
        assertTrue(Sorts.isSorted(one));

        Integer[] sorted = {1,2,3,4,5};
        Sorts.gnomeSort(sorted);
        assertTrue(Sorts.isSorted(sorted));
    }
}
