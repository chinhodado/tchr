package hackerRank.algorithms.warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tdo on 14-Jul-16.
 */
public class TimeConversion {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String converted;
        char c = s.charAt(8);

        if (s.startsWith("12")) {
            if (c == 'P') {
                converted = s.substring(0, 8);
            }
            else {
                converted = "00" + s.substring(2, 8);
            }
        }
        else {
            if (c == 'P') {
                int h = Integer.parseInt(s.substring(0, 2));
                converted = (h + 12) + s.substring(2, 8);
            }
            else {
                converted = s.substring(0, 8);
            }
        }

        System.out.print(converted);
    }
}
