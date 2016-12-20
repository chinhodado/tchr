package hackerRank.contests.misc.rookieRank;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tdo on 26-Jul-16.
 */
public class CountingValleys {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] arr = in.next().toCharArray();
        int level = 0;
        int valleyCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int prev_level = level;
            if (arr[i] == 'U') {
                level++;
            }
            else {
                level--;
            }

            if (level == 0 && prev_level < 0) {
                valleyCount++;
            }
        }
        System.out.println(valleyCount);
    }
}
