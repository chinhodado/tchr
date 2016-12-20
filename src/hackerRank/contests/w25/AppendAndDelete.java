package hackerRank.contests.w25;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 01-Nov-16.
 */
public class AppendAndDelete {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int k = in.nextInt();

        int min = Math.min(s.length, t.length);
        int common = 0;
        for (int i = 0; i < min; i++) {
            if (s[i] == t[i]) {
                common++;
            }
            else {
                break;
            }
        }

        boolean isGood = true;

        int tmp = s.length - common + (t.length - common);
        if (tmp > k) {
            isGood = false;
        }
        else {
            int tmp2 = k - tmp;
            if (tmp2 % 2 == 0) {
                isGood = true;
            }
            else {
                int tmp3 = s.length + t.length;
                if (tmp3 <= k) {
                    isGood = true;
                }
                else {
                    isGood = false;
                }
            }
        }
        System.out.println(isGood? "Yes" : "No");
    }
}
