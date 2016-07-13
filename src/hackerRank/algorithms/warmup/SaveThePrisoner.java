package hackerRank.algorithms.warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 12-Jul-16.
 * Algorithms  -> Warmup ->  Save the Prisoner!
 */
public class SaveThePrisoner {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            int res = (m + s - 1) % n;
            if (res == 0) res = n;
            System.out.println(res);
        }
    }
}
