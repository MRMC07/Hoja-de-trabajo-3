package main.java;

import java.util.Arrays;

public class Sorts {
public static <T extends Comparable<T>> void gnomeSort(T[] arr) {
    int i = 1;
    int j = 2;

    while (i < arr.length) {
        if (arr[i - 1].compareTo(arr[i]) < 0) {
            i = j;
            j++;
        } else {
            T temp = arr[i - 1];
            arr[i - 1] = arr[i];
            arr[i] = temp;
            i--;
            if (i == 0) {
                i = j;
                j++;
            }
        }
    }
}

//Merge Sort
public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr.length <= 1) return;
        T[] aux = Arrays.copyOf(arr, arr.length);
        mergeSortRec(arr, aux, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSortRec(T[] arr, T[] aux, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortRec(arr, aux, left, mid);
        mergeSortRec(arr, aux, mid + 1, right);
        merge(arr, aux, left, mid, right);
    }

private static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int left, int mid, int right) {
    for (int i = left; i <= right; i++) aux[i] = arr[i];

    int i = left;
    int j = mid + 1;
    int k = left;

    while (i <= mid && j <= right) {
        if (aux[i].compareTo(aux[j]) <= 0) arr[k++] = aux[i++];
        else arr[k++] = aux[j++];
    }
    while (i <= mid) arr[k++] = aux[i++];
    // lo de la derecha ya queda
}

//Quick Sort

public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSortRec(T[] arr, int low, int high) {
        if (low >= high) return;

        int p = partition(arr, low, high);
        quickSortRec(arr, low, p - 1);
        quickSortRec(arr, p + 1, high);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        // pivote al final (simple)
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
            swap(arr, i + 1, high);
            return i + 1;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//Radix sort

public static void radixSort(Integer[] arr) {
        if (arr.length <= 1) return;

        // Manejo simple para no negativos (lo usual en Radix). Generá >=0 y listo.
        int max = arr[0];
        for (int v : arr) if (v > max) max = v;

        int exp = 1; // 1,10,100...
        Integer[] output = new Integer[arr.length];

        while (max / exp > 0) {
            int[] count = new int[10];

            for (int v : arr) {
                int digit = (v / exp) % 10;
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) count[i] += count[i - 1];

            for (int i = arr.length - 1; i >= 0; i--) {
                int v = arr[i];
                int digit = (v / exp) % 10;
                output[count[digit] - 1] = v;
                count[digit]--;
            }

        System.arraycopy(output, 0, arr, 0, arr.length);
        exp *= 10;
    }
}

//Heap Sort

public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int n = arr.length;

        // build heap (max heap)
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);

        // extract
        for (int end = n - 1; end > 0; end--) {
            swap(arr, 0, end);
            heapify(arr, end, 0);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l].compareTo(arr[largest]) > 0) largest = l;
        if (r < n && arr[r].compareTo(arr[largest]) > 0) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // ---------- util: verificar orden ----------
    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) return false;
        }
        return true;
    }
}