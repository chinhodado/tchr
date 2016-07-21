package hackerRank.contests.wcs4;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * World CodeSprint #4
 * Minimum Distances
 */
public class MinimumDistances {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int minD = Integer.MAX_VALUE;

        // O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    int distance = Math.abs(i - j);
                    if (distance < minD) {
                        minD = distance;
                    }
                }
            }
        }

        if (minD == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(minD);
        }
    }
}
