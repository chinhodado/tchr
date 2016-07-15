package hackerRank.algorithms.dynamic;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 14-Jul-16.
 */
public class TheMaximumSubarray {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int arr[] = new int[n];

            boolean hasPositive = false;
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                if (arr[i] > 0) hasPositive = true;
            }

            int max = 0;
            if (!hasPositive) {
                max = arr[0];
                for (int x : arr) {
                    if (x > max) max = x;
                }
            }
            else {
                // Kadane's algorithm
                int maxEndingHere = 0;
                for (int i = 0; i < n; i++) {
                    maxEndingHere = Math.max(0, maxEndingHere + arr[i]);
                    max = Math.max(max, maxEndingHere);
                }
            }

            int maxNonContiguous = 0;

            if (!hasPositive) {
                maxNonContiguous = max;
            }
            else {
                for (int x : arr) {
                    if (x > 0) maxNonContiguous += x;
                }
            }

            System.out.println(max + " " + maxNonContiguous);
        }
    }
}
