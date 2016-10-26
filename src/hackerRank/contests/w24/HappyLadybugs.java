package hackerRank.contests.w24;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Chin on 11-Oct-16.
 */
public class HappyLadybugs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for (int nx = 0; nx < g; nx++) {
            int n = in.nextInt();
            String s = in.next();
            char[] arr = s.toCharArray();
            Hashtable<Character, Integer> table = new Hashtable<>();
            int uCount = 0;
            for (char c : arr) {
                if (c == '_') {
                    uCount++;
                    continue;
                }
                if (table.containsKey(c)) {
                    table.put(c, table.get(c) + 1);
                }
                else {
                    table.put(c, 1);
                }
            }

            boolean isGood = true;
            if (uCount == 0) {
                for (int i = 1; i < n - 1; i++) {
                    if (arr[i] == '_') continue;
                    if (arr[i] != arr[i-1] && arr[i] != arr[i+1]) {
                        isGood = false;
                        break;
                    }
                }

                if (n > 1 && arr[0] != '_' && arr[0] != arr[1]) isGood = false;
                if (n > 1 && arr[n-1] != '_' && arr[n-1] != arr[n-2]) isGood = false;
            }
            else {
                for (Integer i : table.values()) {
                    if (i == 1) {
                        isGood = false;
                        break;
                    }
                }
            }

            System.out.println(isGood? "YES" : "NO");
        }
    }
}
