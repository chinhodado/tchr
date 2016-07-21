package hackerRank.algorithms.greedy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by tdo on 21-Jul-16.
 */
public class PriyankaAndToys {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt();
        }

        Arrays.sort(arr);
        int count = 0;
        int i = 0;
        while (i < n) {
            count++;
            int tmp = arr[i];
            while (i < n-1 && arr[i+1] - tmp <= 4) {
                i++;
            }
            i++;
        }
        System.out.println(count);
    }
}
