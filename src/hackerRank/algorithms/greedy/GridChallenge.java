package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class GridChallenge {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            char[][] arr = new char[n][n];

            for (int i = 0; i < n; i++) {
                arr[i] = in.next().toCharArray();
            }

            for (char[] tmp : arr) {
                Arrays.sort(tmp);
            }

            boolean isGood = true;

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] > arr[i + 1][j]) {
                        isGood = false;
                        break;
                    }
                }
            }

            System.out.println(isGood? "YES" : "NO");
        }
    }
}
