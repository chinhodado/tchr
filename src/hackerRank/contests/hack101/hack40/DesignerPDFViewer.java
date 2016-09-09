package hackerRank.contests.hack101.hack40;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tdo on 23-Aug-16.
 */
public class DesignerPDFViewer {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int[] heights = new int[26];
        for (int i = 0; i < 26; i++) {
            heights[i] = in.nextInt();
        }

        char[] s = in.next().toCharArray();
        int max = -1;
        for (char c : s) {
            max = Math.max(heights[c-'a'], max);
        }

        System.out.print(max * s.length);
    }
}
