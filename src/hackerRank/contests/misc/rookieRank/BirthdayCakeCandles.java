package hackerRank.contests.misc.rookieRank;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tdo on 26-Jul-16.
 */
public class BirthdayCakeCandles {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int max = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int h = in.nextInt();
            if (h >= max) {
                if (h > max) {
                    count = 0;
                }
                max = h;
                count++;
            }
        }
        System.out.println(count);
    }
}
