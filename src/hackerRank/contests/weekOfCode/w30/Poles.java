package hackerRank.contests.weekOfCode.w30;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 16-Mar-17.
 */
public class Poles {
    private static long[][] sumCache;
    private static long[][] cache;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        cache = new long[n+1][k+1];
        sumCache = new long[n][n];
        for (long[] tmp : cache) {
            Arrays.fill(tmp, -1);
        }

        int[] alts = new int[n];
        int[] wts = new int[n];
        for (int i = n-1; i >= 0; i--) {
            alts[i] = in.nextInt();
            wts[i] = in.nextInt();
        }

        for (int to = n-1; to >= 0; to--) {
            for (int from = to - 1; from >=0; from--) {
                sumCache[from][to] = sumCache[from+1][to] + (alts[from] - alts[to]) * wts[from];
            }
        }

        System.out.println(M(n, k));
    }

    public static long M(int n, int k) {
        if (n==1) {
            return 0;
        }

        if (cache[n][k] != -1) {
            return cache[n][k];
        }

        if (k==1) {
            return sumCache[0][n-1];
        }

        long min = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            long sumRight = i == n? 0 : sumCache[i][n-1];
            long newSum = M(i, k-1) + sumRight;
            min = Math.min(min, newSum);
        }

        cache[n][k] = min;
        return min;
    }
}
