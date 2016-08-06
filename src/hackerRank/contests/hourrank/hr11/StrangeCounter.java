package hackerRank.contests.hourrank.hr11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 02-Aug-16.
 */
public class StrangeCounter {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        long t = in.nextLong();
        long iteration = (long)(Math.log((t+2)/3.0) / Math.log(2));
        long timeBottom = 3 * (long)Math.pow(2, iteration) - 2;
        long valueBottom = 3 * (long)Math.pow(2, iteration);
        System.out.println(valueBottom - t + timeBottom);
    }
}
