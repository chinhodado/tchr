package hackerRank.contests.rookieRank;

import java.util.Scanner;

/**
 * Created by Chin on 26-Jul-16.
 */
public class AntiprimeNumbers {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        int[] a_arr = new int[q];

        long[] antiprimes = new long[] {
                1L, 2L, 4L, 6L, 12L, 24L, 36L, 48L, 60L, 120L, 180L, 240L, 360L, 720L, 840L, 1260L, 1680L, 2520L, 5040L,
                7560L, 10080L, 15120L, 20160L, 25200L, 27720L, 45360L, 50400L, 55440L, 83160L, 110880L, 166320L, 221760L,
                277200L, 332640L, 498960L, 554400L, 665280L, 720720L, 1081080L, 1441440L, 2162160L, 2882880L, 3603600L,
                4324320L, 6486480L, 7207200L, 8648640L, 10810800L
        };

        for (int i = 0; i < q; i++) {
            a_arr[i] = in.nextInt();
        }

        StringBuilder sb  = new StringBuilder();
        for (int i : a_arr) {
            int idx = 0;
            while (antiprimes[idx] < i && idx < antiprimes.length) {
                idx++;
            }
            sb.append(antiprimes[idx]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
