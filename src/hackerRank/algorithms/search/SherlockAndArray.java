package hackerRank.algorithms.search;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 21-Jul-16.
 */
public class SherlockAndArray {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int arr[] = new int[n];

            long sumRight = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                sumRight += arr[i];
            }

            long sumLeft = 0;
            sumRight -= arr[0];
            boolean isGood = false;
            for (int i = 0; i < n; i++) {
                if (sumLeft == sumRight) {
                    System.out.println("YES");
                    isGood = true;
                    break;
                }

                if (i != n-1) {
                    sumLeft += arr[i];
                    sumRight -= arr[i+1];
                }
            }

            if (!isGood) {
                System.out.println("NO");
            }
        }
    }
}
