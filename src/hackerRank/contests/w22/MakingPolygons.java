package hackerRank.contests.w22;

import java.util.Scanner;

/**
 * Created by Chin on 09-Aug-16.
 */
public class MakingPolygons {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= sum / 2.0) {
                count += Math.ceil(sum / 2.0 / arr[i]);
            }
        }

        System.out.println(n == 1? 2 : count);
    }
}
