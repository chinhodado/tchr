package hackerRank.algorithms.strings;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 30-Jul-16.
 */
public class GameOfThronesI {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        char[] s = in.next().toCharArray();
        int[] count = new int[26];
        for (char value : s) {
            count[value - 'a']++;
        }
        int oddCount = 0;
        for (int i : count) {
            if (i % 2 == 1) {
                oddCount++;
            }
            if (oddCount > 1) break; // shortcut
        }
        System.out.println(oddCount > 1? "NO" : "YES");
    }
}
