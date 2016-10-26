package hackerRank.contests.w24;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 10-Oct-16.
 */
public class AppleAndOrange {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int t = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int n = in.nextInt();
        int[] a_arr = new int[m];
        for (int i = 0; i < m; i++) {
            a_arr[i] = in.nextInt();
        }

        int[] b_arr = new int[n];
        for (int i = 0; i < n; i++) {
            b_arr[i] = in.nextInt();
        }

        int a_count = 0, b_count = 0;
        for (int i = 0; i < m; i++) {
            if (a + a_arr[i] >= s && a + a_arr[i] <= t) {
                a_count++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (b_arr[i] > 0) {
                continue;
            }
            int tmp = Math.abs(b_arr[i]);
            if (b - tmp >= s && b - tmp <= t) {
                b_count++;
            }
        }

        System.out.println(a_count);
        System.out.println(b_count);
    }
}
