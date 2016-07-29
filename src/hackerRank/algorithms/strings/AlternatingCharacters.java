package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 29-Jul-16.
 */
public class AlternatingCharacters {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int tx = 0; tx < t; tx++) {
            String s = in.next();
            char[] arr = s.toCharArray();
            int count = 0;
            int localCount = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i-1]) {
                    localCount++;
                }

                if (arr[i] != arr[i-1] || i == arr.length - 1) {
                    count += localCount;
                    localCount = 0;
                }
            }
            System.out.println(count);
        }
    }
}
