
import java.io.IOException;
import java.util.Arrays;

public class Benchmark {

    // Intervalos: 10..3000 (podés cambiar step si querés)
    public static void runAll(String dataFile, String csvOut, int step) throws IOException {
        FileUtil.writeCsvHeaderIfNeeded(csvOut);

        Integer[] base = FileUtil.readIntegersFile(dataFile);

        for (int n = 10; n <= 3000; n += step) {
            if (n > base.length) break;

            Integer[] sample = Arrays.copyOf(base, n);

            // Caso 1: desordenado (promedio)
            runAllAlgorithms(sample, "unordered", csvOut);

            // Caso 2: ya ordenado (mejor escenario)
            Integer[] alreadySorted = Arrays.copyOf(sample, n);
            Arrays.sort(alreadySorted);
            runAllAlgorithms(alreadySorted, "sorted", csvOut);
        }
    }

    private static void runAllAlgorithms(Integer[] input, String caseName, String csvOut) throws IOException {
        // Se ejecuta 2 veces por algoritmo (como pide la hoja)
        for (int run = 1; run <= 2; run++) {
            // GNOME
            timeAndSave("gnome", input, caseName, run, csvOut, arr -> Sorts.gnomeSort(arr));

            // MERGE
            timeAndSave("merge", input, caseName, run, csvOut, arr -> Sorts.mergeSort(arr));

            // QUICK
            timeAndSave("quick", input, caseName, run, csvOut, arr -> Sorts.quickSort(arr));

            // RADIX (solo Integer)
            timeAndSaveRadix("radix", input, caseName, run, csvOut);

            // HEAP (sort elegido)
            timeAndSave("heap", input, caseName, run, csvOut, arr -> Sorts.heapSort(arr));
        }
    }

    @FunctionalInterface
    interface SortAction {
        void sort(Integer[] arr);
    }

    private static void timeAndSave(String name, Integer[] input, String caseName, int run, String csvOut, SortAction action) throws IOException {
        Integer[] copy = Arrays.copyOf(input, input.length);

        long start = System.nanoTime();
        action.sort(copy);
        long end = System.nanoTime();

        if (!Sorts.isSorted(copy)) throw new IllegalStateException("No ordenó bien: " + name);

        FileUtil.appendCsvRow(csvOut, name, copy.length, caseName, run, end - start);
    }

    private static void timeAndSaveRadix(String name, Integer[] input, String caseName, int run, String csvOut) throws IOException {
        Integer[] copy = Arrays.copyOf(input, input.length);

        long start = System.nanoTime();
        Sorts.radixSort(copy);
        long end = System.nanoTime();

        if (!Sorts.isSorted(copy)) throw new IllegalStateException("No ordenó bien: " + name);

        FileUtil.appendCsvRow(csvOut, name, copy.length, caseName, run, end - start);
    }
}
