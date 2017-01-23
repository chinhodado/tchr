package hackerRank.contests.weekOfCode.w28;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 11-Jan-17.
 */
public class LuckyNumberEight {
    static int n;
    static int[] arr;
    static int[][] dp;
    static final int k = 8;
    static final int toMod = 1000000007;

    static int fun(int idx, int m) {
        // One sub-sequence completed
        if (idx == n) {
            // return 1 only if modulus by n is 0
            return m == 0 ? 1 : 0;
        }

        if (dp[idx][m] != -1)
            return dp[idx][m];

        // skip this element in current sub-sequence
        int ans = fun(idx + 1, m);

        // Include this element. Find the new modulo by 'n' and pass it recursively
        ans += fun(idx + 1, (m * 10 + arr[idx]) % k);
        ans %= toMod;

        return dp[idx][m] = ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        char[] s = in.next().toCharArray();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s[i] - '0';
        }

        dp = new int[n][k];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }

        // initially we begin by considering array of length 1 i.e. upto index 0
        System.out.println(fun(0, 0) - 1);
    }
}
