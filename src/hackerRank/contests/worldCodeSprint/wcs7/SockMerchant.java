package hackerRank.contests.worldCodeSprint.wcs7;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Chin on 24-Sep-16.
 */
public class SockMerchant {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            if (table.containsKey(t)) {
                table.put(t, table.get(t) + 1);
            }
            else {
                table.put(t, 1);
            }
        }

        int count = 0;
        for (Integer t : table.values()) {
            count += (t / 2);
        }

        System.out.println(count);
    }
}
