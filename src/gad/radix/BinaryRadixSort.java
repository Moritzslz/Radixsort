package gad.radix;

import java.util.Arrays;
import java.util.Random;

public final class BinaryRadixSort {

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        return (element >> binPlace) & 1;
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        for (int i = 0; i < from.getSize(); i++) {
            int value = from.getValue(i);
            int bit = key(value, binPlace);
            if (bit == 1)
                to.insertRight(value);
            else {
                to.insertLeft(value);
            }
        }
    }

    public static void lastSort(BinaryBucket from, int[] to) {

    }

    public static void sort(int[] elements, Result result) {
        BinaryBucket from = new BinaryBucket(elements.length);
        BinaryBucket to = new BinaryBucket(elements.length);
        for (int l = 0; l < elements.length; l++) {
            from.insertLeft(elements[l]);
        }
        for (int i = 0; i < 32; i++) {
            for (int k = 0; k < elements.length; k++) {
                kSort(from, to, i);
            }
            from = to;
        }
    }

    public static void main(String[] args) {
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
