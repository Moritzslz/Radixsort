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
            if (bit == 0) {
                to.insertLeft(value);
            } else {
                to.insertRight(value);
            }
        }
    }

    public static void lastSort(BinaryBucket from, int[] to) {
    }

    public static void sort(int[] elements, Result result) {
        //Init Buckets
        BinaryBucket from = new BinaryBucket(elements.length);
        BinaryBucket to = new BinaryBucket(elements.length);
        from.setBucket(elements);

        for (int bitIdx = 0; bitIdx < 32; bitIdx++) {
            kSort(from, to, bitIdx);
            from = to;
        }

        lastSort(from, elements);
        result.logArray(elements);
    }

    public static void main(String[] args) {
        int[] elements = new int[10];
        Random element = new Random();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = element.nextInt(200);
        }

        System.out.println("Start: " + Arrays.toString(elements));
        StudentResult studentResult = new StudentResult();
        sort(elements, studentResult);

        /*
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

         */
    }
}
