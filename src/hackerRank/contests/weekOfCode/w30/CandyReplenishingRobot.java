package hackerRank.contests.weekOfCode.w30;

import java.util.Scanner;

/**
 * Created by Chin on 14-Mar-17.
 */
public class CandyReplenishingRobot {
    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
        }

        int count = 0;
        int current = n;
        for (int i = 0; i < t-1; i++) {
            current -= arr[i];
            if (current < 5) {
                count += (n - current);
                current = n;
            }
        }

        System.out.println(count);
    }
}
