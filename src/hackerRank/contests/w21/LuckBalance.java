package hackerRank.contests.w21;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LuckBalance {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];

        int total = 0;
        for (int i = 0; i < n; i++) {
            int l = in.nextInt();
            int t = in.nextInt();

            if (t == 0) {
                total += l;
            }
            else {
                arr[i] = l;
            }
        }

        Arrays.sort(arr);

        for (int i = n-1; i >= n-k; i--) {
            total += arr[i];
        }

        for (int i = n-k-1; i >= 0; i--) {
            total -= arr[i];
        }

        System.out.println(total);
    }
}
