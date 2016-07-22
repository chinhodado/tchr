package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class GreedyFlorist {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        int sum = 0;
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            sum += (1 + count/k) * arr[i];
            count++;
        }

        System.out.println(sum);
    }
}
