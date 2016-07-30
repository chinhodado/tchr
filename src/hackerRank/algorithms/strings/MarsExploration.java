package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 29-Jul-16.
 */
public class MarsExploration {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        char[] s = in.next().toCharArray();
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            if (i % 3 == 1) {
                if (s[i] != 'O')
                    count++;
            }
            else {
                if (s[i] != 'S')
                    count++;
            }
        }
        System.out.println(count);
    }
}
