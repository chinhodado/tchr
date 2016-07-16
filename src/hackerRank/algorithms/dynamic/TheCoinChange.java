package hackerRank.algorithms.dynamic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 16-Jul-16.
 */
public class TheCoinChange {
    static int[] arr;
    static long[][] cache;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
        }

        cache = new long[m][n + 1];
        for (long[] tmp : cache)
            Arrays.fill(tmp, -1);

        System.out.println(count(m - 1, n));
    }

    // Returns the count of ways we can sum  S[0...r] coins to get sum x
    static long count(int r, int x) {
        if (x == 0)
            return 1;

        if (x < 0)
            return 0;

        if (r <= -1 && x >= 1)
            return 0;

        long tmp1 = 0; // covers r <= 0
        if (r > 0) {
            tmp1 = cache[r-1][x];
            if (tmp1 == -1) {
                tmp1 = count(r-1, x);
            }
        }

        long tmp2 = 0; // cover x < 0
        if (x-arr[r] >= 0) {
            tmp2 = cache[r][x - arr[r]];
            if (tmp2 == -1) {
                tmp2 = count(r, x - arr[r]);
            }
        }

        cache[r][x] = tmp1 + tmp2;
        return cache[r][x];
    }
}
