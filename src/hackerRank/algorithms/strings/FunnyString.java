package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Chin on 29-Jul-16.
 */
public class FunnyString {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            char[] arr = in.next().toCharArray();
            int n = arr.length;
            int mid = (int)Math.ceil(n/2.0);
            boolean isGood = true;
            for (int i = 1; i < mid && isGood; i++) {
                int x = Math.abs(arr[i] - arr[i-1]);
                int y = Math.abs(arr[n-i-1] - arr[n-i]);
                if (x != y) isGood = false;
            }

            System.out.println(isGood? "Funny" : "Not Funny");
        }
    }
}
