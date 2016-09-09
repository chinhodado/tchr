package hackerRank.contests.hack101.hack40;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by tdo on 23-Aug-16.
 */
public class LazyMayorAndLasers {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = in.nextInt();
        }

        int m = in.nextInt();
        int[] lasers = new int[m];
        for (int i = 0; i < m; i++) {
            lasers[i] = in.nextInt() - 1;
        }
        Arrays.sort(lasers);

        long sum = 0;

        int lastLaserPos = 0;
        for (int laserPos : lasers) {
            for (int j = lastLaserPos; j < laserPos; j++) {
                long newHeight = Math.min(heights[j], (laserPos - j));
                sum += newHeight;
            }
            lastLaserPos = laserPos;
        }

        for (int i = lastLaserPos; i < n; i++) {
            sum += heights[i];
        }

        System.out.print(sum);
    }
}
