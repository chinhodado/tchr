package hackerRank.contests.w25;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 31-Oct-16.
 */
public class BetweenTwoSets {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }

        int gcd = b[0];
        for (int i = 1; i < b.length; i++) {
            gcd = gcd(gcd, b[i]);
        }

        int lcm = a[0];
        for (int i = 1; i < a.length; i++) {
            lcm = lcm(lcm, a[i]);
        }

        if (gcd % lcm != 0) {
            System.out.println(0);
        }
        else {
            int count = 0;
            for (int i = 1; i * lcm <= gcd; i++) {
                int tmp = i* lcm;
                if (gcd % tmp == 0) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
