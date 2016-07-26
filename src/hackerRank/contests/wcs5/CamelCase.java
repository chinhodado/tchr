package hackerRank.contests.wcs5;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 23-Jul-16.
 * TODO: bad running time
 */
public class CamelCase {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        char[] arr = s.toCharArray();
        int count = 0;
        for (char c : arr) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }

        System.out.println(count + 1);
    }
}
