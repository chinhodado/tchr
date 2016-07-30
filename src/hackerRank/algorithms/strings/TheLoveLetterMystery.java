package hackerRank.algorithms.strings;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 30-Jul-16.
 */
public class TheLoveLetterMystery {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int tx = 0; tx < t; tx++) {
            char[] s = in.next().toCharArray();
            int sum = 0;
            int n = s.length;
            for (int i = 0; i < n/2; i++) {
                sum += Math.abs(s[i] - s[n-i-1]);
            }
            System.out.println(sum);
        }
    }
}
