package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class MaxMin {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        int unfairness = Integer.MAX_VALUE;
        int i = 0, j = k - 1;

        while (j < n) {
            unfairness = Math.min(unfairness, arr[j] - arr[i]);
            i++;
            j++;
        }

        System.out.println(unfairness);
    }
}
