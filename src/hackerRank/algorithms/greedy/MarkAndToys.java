package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class MarkAndToys {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = in.nextInt();
        }

        Arrays.sort(prices);
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= k) {
                count++;
                k -= prices[i];
            }
            else {
                break;
            }
        }

        System.out.println(count);
    }
}
