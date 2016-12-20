package hackerRank.contests.weekOfCode.w22;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 10-Aug-16.
 */
public class MatchingSets {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] xs = new long[n];
        long sum_x = 0;
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextLong();
            sum_x += xs[i];
        }

        long[] ys = new long[n];
        long sum_y = 0;
        for (int i = 0; i < n; i++) {
            ys[i] = in.nextLong();
            sum_y += ys[i];
        }

        if (sum_x != sum_y) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(xs);
        Arrays.sort(ys);

        long total_diff = 0;
        for (int i = 0; i < n; i++) {
            total_diff += Math.abs(xs[i] - ys[i]);
        }

        System.out.println(total_diff / 2);
    }
}
