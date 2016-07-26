package hackerRank.contests.wcs5;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 23-Jul-16.
 */
public class StringConstruction {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            char[] arr = s.toCharArray();
            int count = 1;
            for (int j = 1; j < arr.length; j++) {
                boolean found = false;
                // can probably use a set instead of looping to improve performance...
                for (int k = 0; k < j; k++) {
                    if (arr[j] == arr[k]) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    continue;
                }
                else {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
