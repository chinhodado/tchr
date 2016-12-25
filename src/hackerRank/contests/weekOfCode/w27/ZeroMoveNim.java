package hackerRank.contests.weekOfCode.w27;

import java.util.Scanner;

/**
 * Created by Chin on 22-Dec-16.
 */
public class ZeroMoveNim {
    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for (int gx = 0; gx < g; gx++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            int[] nimbers = new int[n];
            for (int i = 0; i < n; i++) {
                if (arr[i] % 2 == 0) {
                    nimbers[i] = arr[i] - 1;
                }
                else {
                    nimbers[i] = arr[i] + 1;
                }
            }

            int xorsum = nimbers[0];
            for (int i = 1; i < n; i++) {
                xorsum ^= nimbers[i];
            }

            if (xorsum == 0) {
                System.out.println("L");
            }
            else {
                System.out.println("W");
            }
        }
    }
}
