package hackerRank.contests.weekOfCode.w29;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 22-Feb-17.
 */
public class BigSorting {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next();
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1.length() < o2.length()) {
                return -1;
            }

            if (o1.length() > o2.length()) {
                return 1;
            }
            return o1.compareTo(o2);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }
}
