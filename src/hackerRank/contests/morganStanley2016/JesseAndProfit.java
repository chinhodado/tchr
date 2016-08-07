package hackerRank.contests.morganStanley2016;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 06-Aug-16.
 */
public class JesseAndProfit {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        long[] n_arr = new long[n];
        long[] sorted_arr = new long[n];
        for (int i = 0; i < n; i++) {
            n_arr[i] = in.nextLong();
            sorted_arr[i] = n_arr[i];
        }

        int[] d_arr = new int[d];
        for (int i = 0; i < d; i++) {
            d_arr[i] = in.nextInt();
        }

        Arrays.sort(sorted_arr);

        for (int dx = 0; dx < d; dx++) {
            int di = d_arr[dx];
            int min = Integer.MAX_VALUE;
            int final_i = -1;
            int final_k = -1;
            boolean done = false;
            // binary search for the value in the sorted array, then linear search for it in the original array
            // which is O(nlogn)
            for (int i = 0; i < n && !done; i++) {
                long x = di + n_arr[i];
                int idx_in_sorted = Arrays.binarySearch(sorted_arr, x);
                if (idx_in_sorted >= 0) {
                    for (int k = i + 1; k < n && !done; k++) {
                        if (n_arr[k] == x) {
                            if (k - i < min) {
                                final_i = i;
                                final_k = k;
                                min = k - i;
                            }
                            if (k - i == 1) {
                                done = true;
                            }
                        }
                    }
                }
            }

            if (final_i == -1) {
                System.out.println(-1);
            }
            else {
                System.out.println((final_i + 1) + " " + (final_k + 1));
            }
        }
    }
}
