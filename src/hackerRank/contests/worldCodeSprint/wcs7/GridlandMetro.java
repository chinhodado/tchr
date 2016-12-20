package hackerRank.contests.worldCodeSprint.wcs7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Chin on 25-Sep-16.
 * TODO: wrong solution. Haven't accounted for non-overlapping tracks yet
 */
public class GridlandMetro {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        long m = in.nextInt();
        int k = in.nextInt();
        Hashtable<Integer, ArrayList<Integer[]>> rowLimits = new Hashtable<>();

        for (int i = 0; i < k; i++) {
            int r = in.nextInt() - 1;
            int c1 = in.nextInt() - 1;
            int c2 = in.nextInt() - 1;
            int left = Math.min(c1, c2);
            int right = Math.max(c1, c2);

            if (rowLimits.containsKey(r)) {
                Integer[] lims = rowLimits.get(r).get(0);
                lims[0] = Math.min(lims[0], left);
                lims[1] = Math.max(lims[1], right);
            }
            else {
                ArrayList<Integer[]> tmpList = new ArrayList<>();
                tmpList.add(new Integer[] {left, right});
                rowLimits.put(r, tmpList);
            }
        }

        long count = 0;
        for (int r : rowLimits.keySet()) {
            Integer[] lims = rowLimits.get(r).get(0);
            count += (m - (lims[1] - lims[0] + 1));
        }

        int occupiedSize = rowLimits.keySet().size();
        count += (n - occupiedSize) * m;

        System.out.println(count);
    }
}
