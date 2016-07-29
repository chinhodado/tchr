package hackerRank.algorithms.strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 28-Jul-16.
 */
public class SuperReducedString {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        char[] arr = in.next().toCharArray();
        boolean isDone = true;
        int last = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                // not 100% sure about the logic at this place. Anyway...
                if (i == arr.length - 1 && !isDone) {
                    isDone = true;
                    i = 0;
                    last = 0;
                }
                continue;
            }
            if (arr[i] == arr[last]) {
                arr[i] = 0;
                arr[last] = 0;
                isDone = false;
            }

            last = i;
            if (i == arr.length - 1 && !isDone) {
                isDone = true;
                i = 0;
                last = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != 0) {
                sb.append(c);
            }
        }

        String s = sb.toString();
        if (s.equals("")) s = "Empty String";
        System.out.println(s);
    }
}
