package hackerRank;

import dataStructure.SegmentTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Chin on 04-Jan-17.
 */
public class RangeMinimumQuery {
    public static void main(String args[]) throws FileNotFoundException {
//        System.setIn(new FileInputStream("a.txt"));
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = in.nextInt();
//        }
//        SegmentTree tree = new SegmentTree(arr);
//        for (int i = 0; i < m; i++) {
//            int from = in.nextInt();
//            int to = in.nextInt();
//            int min = tree.getMin(from, to)[0];
//            System.out.println(min);
//        }
        test();
    }

    static void test() {
        Random rand = new Random();
        long totalNaiveTime = 0;
        long totalSegTime = 0;
        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(10000) + 10000;
            int[] arr = new int[size];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = rand.nextInt(10000);
            }

            System.out.println("Testing min query");
            SegmentTree st = new SegmentTree(arr);
            for (int j = 0; j < 1000; j++) {
                int from = rand.nextInt(size);
                int to = rand.nextInt(size);
                if (from > to) {
                    int tmp = from;
                    from = to;
                    to = tmp;
                }

                long start = System.currentTimeMillis();
                int minNaive = naiveMin(arr, from, to);
                long end = System.currentTimeMillis();
                totalNaiveTime += (end-start);

                start = System.currentTimeMillis();
                int[] stRes = st.getMin(from, to);
                end = System.currentTimeMillis();
                totalSegTime += (end-start);

                if (minNaive != stRes[0]) {
                    throw new Error("Wrong min: expected " + minNaive + ", got " + stRes[0]);
                }

                if (stRes[0] != arr[stRes[1]]) {
                    throw new Error("Wrong index");
                }
            }

            System.out.println("Testing range update");
            for (int j = 0; j < 1000; j++) {
                int from = rand.nextInt(size);
                int to = rand.nextInt(size);
                if (from > to) {
                    int tmp = from;
                    from = to;
                    to = tmp;
                }

                int fromMin = randInt(rand, 0, (from+to)/2);
                int toMin = randInt(rand, (from+to)/2, size-1);

                int newValue = rand.nextInt(10000);
                long start = System.currentTimeMillis();
                naiveUpdate(arr, from, to, newValue);
                int minNaive = naiveMin(arr, fromMin, toMin);
                long end = System.currentTimeMillis();
                totalNaiveTime += (end-start);

                start = System.currentTimeMillis();
                st.update(from, to, newValue);
                int[] stRes = st.getMin(fromMin, toMin);
                end = System.currentTimeMillis();
                totalSegTime += (end-start);

                if (minNaive != stRes[0]) {
                    throw new Error("Wrong min: expected " + minNaive + ", got " + stRes[0]);
                }

                if (stRes[0] != arr[stRes[1]]) {
                    throw new Error("Wrong index");
                }
            }
        }

        System.out.println("Test done.");
        System.out.println("Total naive time: " + totalNaiveTime + "ms");
        System.out.println("Total segtree time: " + totalSegTime + "ms");
    }

    static int naiveMin(int[] arr, int from, int to) {
        int min = arr[from];
        for (int i = from; i <= to; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    static void naiveUpdate(int[] arr, int from, int to, int newValue) {
        for (int i = from; i <= to; i++) {
            arr[i] = newValue;
        }
    }

    static int randInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
