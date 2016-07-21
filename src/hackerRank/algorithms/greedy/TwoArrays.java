package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by tdo on 21-Jul-16.
 */
public class TwoArrays {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a_arr = new int[n];
            Integer[] b_arr = new Integer[n];

            for (int i = 0; i < n; i++) {
                a_arr[i] = in.nextInt();
            }

            for (int i = 0; i < n; i++) {
                b_arr[i] = in.nextInt();
            }

            Arrays.sort(a_arr);
            Arrays.sort(b_arr, Collections.reverseOrder());

            boolean isGood = true;
            for (int i = 0; i < n; i++) {
                if (a_arr[i] + b_arr[i] < k) {
                    isGood = false;
                    break;
                }
            }

            if (isGood) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
