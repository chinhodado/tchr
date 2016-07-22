package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class BeautifulPairs {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a_arr = new int[n];
        int[] b_arr = new int[n];

        for (int i = 0; i < n; i++) {
            a_arr[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b_arr[i] = in.nextInt();
        }

        Arrays.sort(a_arr);
        Arrays.sort(b_arr);
        int i = 0, j = 0;
        int j_min = 0;
        int k = 0;

        while (i < n) {
            if (a_arr[i] == b_arr[j]) {
                k++;
                i++;
                j++;
                j_min = j;
            }
            else {
                j++;

                if (j == n) {
                    i++;
                    j = j_min;
                }
            }
        }

        if (k == n) {
            k--;
        }
        else {
            k++;
        }

        System.out.println(k);
    }
}
