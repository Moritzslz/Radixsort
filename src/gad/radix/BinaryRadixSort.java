package gad.radix;

import java.util.Arrays;
import java.util.Random;

public final class BinaryRadixSort {

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        return 0;
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {

    }

    public static void lastSort(BinaryBucket from, int[] to) {

    }

    public static void sort(int[] elements, Result result) {

    }

    public static void main(String[] args) {
        int[] testArray = new int[] {1, 2, 34, 532, 2222, 1, 0, 34567};
        int[] testArray2 = new int[] {};
        int[] testArray3 = new int[] {-1, -2, -34, 532, -2222, -1, -0, -34567};
        int[] testArray4 = new int[] {0, 0, 0, 0, 0, 0, 0};
        int[] testArray5 = new int[] {0123, 93, 123};
        System.out.println(RadixSort.key(123, 3));
        System.out.println(RadixSort.getMaxDecimalPlaces(testArray5));

        int[] test = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int[] testTwo = Arrays.copyOf(test, test.length);

        long start = System.nanoTime();
        sort(test, ignored -> {
        });
        long binaryTime = System.nanoTime() - start;

        start = System.nanoTime();
        RadixSort.sort(testTwo, ignored -> {
        });
        long decimalTime = System.nanoTime() - start;

        System.out.println("Korrekt sortiert:" + Arrays.equals(test, testTwo));
        System.out.println("Binary: " + binaryTime / 1_000_000);
        System.out.println("Decimal: " + decimalTime / 1_000_000);
    }
}
