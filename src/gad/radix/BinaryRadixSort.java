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
        int size = from.getSize();

        for (int i = 0; i < size; i++) {
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
        int size = from.getSize();
        int left = 0;
        int right = size - 1;

        for (int i = 0; i < size; i++) {
            int value = from.getValue(i);

            if (value < 0) {
                to[right] = value;
                right--;
            } else {
                to[left] = value;
                left++;
            }
        }
    }

    public static void sort(int[] elements, Result result) {
        BinaryBucket from = new BinaryBucket(elements.length);
        BinaryBucket to = new BinaryBucket(elements.length);

        // Initialize from bucket with the array
        from.setBucket(elements);

        // Perform kSort for each binary place
        for (int binPlace = 0; binPlace < 32; binPlace++) {
            kSort(from, to, binPlace);
            result.logArray(to.getBucket());

            // Swap the from and to buckets for the next iteration
            BinaryBucket temp = from;
            from = to;
            to = temp;
        }

        // Handle negative numbers using lastSort
        lastSort(from, elements);
        result.logArray(elements);
    }

    public static void main(String[] args) {
        // Own test implementation
        int[] elements = new int[10];
        Random element = new Random();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = element.nextInt(200);
        }

        StudentResult studentResult = new StudentResult();
        sort(elements, studentResult);

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
