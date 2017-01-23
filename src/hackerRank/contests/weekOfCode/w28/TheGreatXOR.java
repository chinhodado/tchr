package hackerRank.contests.weekOfCode.w28;

import java.util.Scanner;

/**
 * Created by Chin on 10-Jan-17.
 */
public class TheGreatXOR {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long x = in.nextLong();
            int t = 0;
            long tmp = x;
            long sum = 0;
            while (x != 0) {
                long lsb = x & 1;
                if (lsb == 0) {
                    sum += Math.pow(2, t);
                }
                x = x >> 1;
                t++;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
