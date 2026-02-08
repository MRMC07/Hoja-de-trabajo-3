package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileUtil {

    // Genera enteros NO negativos (ideal para Radix)
    public static void generateRandomNumbersFile(String path, int n, int maxValue, long seed) throws IOException {
        if (n < 1 || n > 3000) throw new IllegalArgumentException("n debe estar entre 1 y 3000");
        Random rnd = new Random(seed);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < n; i++) {
                int value = rnd.nextInt(maxValue + 1); // 0..maxValue
                bw.write(Integer.toString(value));
                bw.newLine();
            }
        }
    }

    public static Integer[] readIntegersFile(String path) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) list.add(Integer.parseInt(line));
            }
        }
        return list.toArray(new Integer[0]);
    }

    public static void writeCsvHeaderIfNeeded(String csvPath) throws IOException {
        File f = new File(csvPath);
        if (f.exists() && f.length() > 0) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvPath, true))) {
            bw.write("algorithm,n,case,run,time_ns");
            bw.newLine();
        }
    }

    public static void appendCsvRow(String csvPath, String algorithm, int n, String caseName, int run, long timeNs) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvPath, true))) {
            bw.write(algorithm + "," + n + "," + caseName + "," + run + "," + timeNs);
            bw.newLine();
        }
    }
}
