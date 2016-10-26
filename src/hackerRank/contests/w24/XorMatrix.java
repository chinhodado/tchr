package hackerRank.contests.w24;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 13-Oct-16.
 */
public class XorMatrix {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long m = in.nextLong();

        if (m > n && (n & (n - 1)) == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append("0").append(" ");
            }
            System.out.println(sb);
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] oriSecond = new int[n];
        for (int i = 0; i < n; i++) {
            oriSecond[i] = arr[i] ^ arr[(i + 1) % n];
        }
        int maxToTest = Math.min(n, 5);

        long rowsToContinue = -1;
        for (long mx = 1; mx < m; mx++) {
            int[] newArr = new int[n];
            for (int i = 0; i < n; i++) {
                newArr[i] = arr[i] ^ arr[(i+1)%n];
            }
            arr = newArr;

            if (mx == 1) continue;

            boolean isGood = true;
            for (int i = 0; i < maxToTest; i++) {
                if (arr[i] != oriSecond[i]) {
                    isGood = false;
                    break;
                }
            }

            if (isGood) {
                long period = mx - 1;
                long rowsLeft = m - 1 - mx;
                rowsToContinue = rowsLeft % period;
                break;
            }
        }

        if (rowsToContinue != -1) {
            for (long mx = 0; mx < rowsToContinue; mx++) {
                int[] newArr = new int[n];
                for (int i = 0; i < n; i++) {
                    newArr[i] = arr[i] ^ arr[(i + 1) % n];
                }
                arr = newArr;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
