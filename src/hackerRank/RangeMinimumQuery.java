package hackerRank;

import dataStructure.SegmentTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 04-Jan-17.
 */
public class RangeMinimumQuery {
    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        SegmentTree tree = new SegmentTree(arr);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int min = tree.getMin(from, to);
            System.out.println(min);
        }
    }
}
