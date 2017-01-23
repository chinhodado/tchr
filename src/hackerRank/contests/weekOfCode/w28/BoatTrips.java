package hackerRank.contests.weekOfCode.w28;

import java.util.Scanner;

/**
 * Created by Chin on 10-Jan-17.
 */
public class BoatTrips {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        boolean isGood = true;
        for (int i : arr) {
            if (i > m*c) {
                isGood = false;
                break;
            }
        }

        System.out.println(isGood? "Yes" : "No");
    }
}
