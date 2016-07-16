package hackerRank.algorithms.dynamic;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 16-Jul-16.
 * Note: there's nothing "dynamic" about this solution, just plain old recursion
 */
public class NikitaAndTheGame {
    static long[] sum;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            sum = new long[n];
            sum[0] = in.nextInt();
            for (int i = 1; i < n; i++) {
                sum[i] = in.nextInt() + sum[i - 1];
            }
            System.out.println(solve(0, n-1));
        }
    }

    static long sum(int l, int r) {
        return sum[r] - (l == 0? 0 : sum[l-1]);
    }

    // binary search to find first valid X within subarray [l, r]
    static int get(int l, int r, long s) {
        int low, high, mid;
        low = l;
        high = r;
        while (low <= high) {
            mid = (low + high) / 2;
            long x = sum(l, mid);
            if(x == s && (mid == l || sum(l, mid-1) != s )) {
                return mid;
            }
            else if (x >= s) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static int solve(int l, int r){
        long s = sum(l, r);
        if( l != r && s % 2 == 0 ) {
            int ind = get(l, r, s/2);
            if( ind != -1 ) {
                // compute maximum from 2 parts
                return Math.max(solve(l, ind), solve(ind+1, r)) + 1;
            }
        }
        return 0;
    }
}
