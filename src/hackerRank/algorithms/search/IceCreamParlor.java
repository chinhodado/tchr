package hackerRank.algorithms.search;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 22-Jul-16.
 */
public class IceCreamParlor {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int m = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            int[] sorted_arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                sorted_arr[i] = arr[i];
            }

            Arrays.sort(sorted_arr);
            boolean done = false;
            // binary search for the value in the sorted array, then linear search for it in the original array
            // which is O(nlogn)
            for (int i = 0; i < n && !done; i++) {
                int x = m - arr[i];
                int j = Arrays.binarySearch(sorted_arr, x);
                if (j >= 0) {
                    int y = sorted_arr[j];
                    for (int k = i + 1; k < n && !done; k++) {
                        if (arr[k] == y && k != i) {
                            System.out.println((i+1) + " " + (k+1));
                            done = true;
                        }
                    }
                }
            }
        }
    }
}
