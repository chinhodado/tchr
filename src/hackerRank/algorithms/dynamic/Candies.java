package hackerRank.algorithms.dynamic;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 16-Jul-16.
 */
public class Candies {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] sol = new int[n];
        sol[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i-1]) {
                sol[i] = sol[i-1] + 1;
            }
            else {
                sol[i] = 1;
            }
        }

        for (int i = n-2; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {
                sol[i] = Math.max(sol[i+1] + 1, sol[i]);
            }
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sol[i];
        }
        System.out.println(sum);
    }
}
