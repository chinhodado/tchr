package hackerRank.contests.hourrank.hr10;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 06-Jul-16.
 */
public class JumpingOnTheCloudsRevisited {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int e = 100;
        int i = 0;
        while (true) {
            e--;
            i = (i+k) % n;
            if (arr[i] == 1) {
                e -= 2;
            }

            if (i == 0) {
                break;
            }
        }
        System.out.println(e);
    }
}
