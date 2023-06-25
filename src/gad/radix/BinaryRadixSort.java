package gad.radix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class BinaryRadixSort {

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        return (element >> binPlace) & 1;
    }

    public static void concatenate(List<Integer>[] buckets, int[] elements) {
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                elements[k++] = buckets[i].get(j);
            }
        }
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        final int DIGITS = 2; // Anzahl der Binärstellen (0 und 1)
        List<Integer>[] buckets = new List[DIGITS];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(from.getSize() / DIGITS);
        }

        for (int i = 0; i < from.getSize(); i++) {
            buckets[key(from.getValue(i), binPlace)].add(from.getValue(i));
        }

        concatenate(buckets, to.getBucket());
        /*
        for (int i = 0; i < from.getSize(); i++) {
            int value = from.getValue(i);
            int bit = key(value, binPlace);
            if (bit == 0) {
                to.insertLeft(value);
            } else {
                to.insertRight(value);
            }
        }
         */
    }

    public static void lastSort(BinaryBucket from, int[] to) {
        BinaryBucket bucket = new BinaryBucket(to.length);
        for (int i = 0; i < from.getSize(); i++) {
            int value = from.getValue(i);
            int bit = key(value, 31);
            if (bit == 0) {
                bucket.insertRight(value);
            } else {
                bucket.insertLeft(value);
            }
        }
        int[] temp = bucket.getBucket();
        for (int i = 0; i < to.length; i++) {
            to[i] = temp[temp.length - 1 - i];
        }
    }

    public static void sort(int[] elements, Result result) {
        //Init Buckets
        BinaryBucket from = new BinaryBucket(elements.length);
        from.setBucket(elements);

        for (int bitIdx = 0; bitIdx < 32; bitIdx++) {
            BinaryBucket to = new BinaryBucket(elements.length);
            kSort(from, to, bitIdx);
            from.setBucket(to.getBucket());
            result.logArray(from.getBucket());
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
