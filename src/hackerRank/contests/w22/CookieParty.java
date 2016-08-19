package hackerRank.contests.w22;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by tdo on 08-Aug-16.
 */
public class CookieParty {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int res = 0;
        if (m % n != 0) {
            int each = m / n + 1;
            res = each * n - m;
        }
        System.out.println(res);
    }
}
