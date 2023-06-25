package gad.radix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class BinaryRadixSort {

    public static List<Integer>[] buckets = new List[2];
    private static boolean containsNegative;

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        return (element >> binPlace) & 1;
    }

    public static void concatenate(int[] elements) {
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                elements[k++] = buckets[i].get(j);
            }
        }
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        buckets[0] = new ArrayList<>(from.getSize() / 2);
        buckets[1] = new ArrayList<>(from.getSize() / 2);

        if (binPlace != 31) {
            for (int i = 0; i < from.getSize(); i++) {
                buckets[key(from.getValue(i), binPlace)].add(from.getValue(i));
            }
        } else {
            for (int i = 0; i < from.getSize(); i++) {
                int key = key(from.getValue(i), binPlace);
                if (key == 1) {
                    containsNegative = true;
                }
                buckets[key].add(from.getValue(i));
            }
        }


        concatenate(to.getBucket());

         /*
        for (int i = 0; i < from.getSize(); i++) {
            int v = key(from.getValue(i), binPlace);
            if (v == 0) {
                to.insertLeft(v);
            } else {
                to.insertRight(v);
            }
        }

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
        int k = 0;
        int j = 0;
       for (int i = from.getSize() - 1; i >= 0 ; i--){
           if (from.getValue(i) < 0) {
               to[k] = from.getValue(i);
               k++;
           } else {
               to[k] = from.getValue(j);
               k++;
               j++;
           }
       }
    }

    public static void sort(int[] elements, Result result) {
        //Init Buckets
        BinaryBucket from = new BinaryBucket(elements.length);
        from.setBucket(elements);
        containsNegative = false;

        for (int bitIdx = 0; bitIdx < 32; bitIdx++) {
            BinaryBucket to = new BinaryBucket(elements.length);
            kSort(from, to, bitIdx);
            from.setBucket(to.getBucket());
            result.logArray(from.getBucket());
        }

        if (containsNegative) {
            lastSort(from, elements);
            result.logArray(elements);
        }
    }

    public static void main(String[] args) {

        int[] elements = new int[10];
        elements[0] = -10;
        elements[1] = -9;
        Random element = new Random();
        for (int i = 2; i < elements.length; i++) {
            elements[i] = element.nextInt(200);
        }

        System.out.println("Start: " + Arrays.toString(elements));
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
