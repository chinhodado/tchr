package hackerRank.hack39;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 12-Jul-16.
 * 101 Hack 39
 * Equalize the Array
 */
public class EqualizeTheArray {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] count = new int[100];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt() - 1;
            count[arr[i]]++;
        }

        int maxIndex = 0;

        for (int i = 1; i < count.length; i++) {
            if (count[i] > count[maxIndex]) {
                maxIndex = i;
            }
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != maxIndex) {
                res++;
            }
        }

        System.out.print(res);
    }
}
