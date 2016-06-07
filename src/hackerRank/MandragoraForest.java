package hackerRank;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MandragoraForest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int testCase = 0; testCase < n; testCase++){
            int numM = in.nextInt();
            int[] arr = new int[numM];

            for (int i = 0; i < numM; i++) {
                arr[i] = in.nextInt();
            }

            Arrays.sort(arr);
            long maxP = 0;

            for (int i = 0; i < numM; i++) {
                maxP += arr[i];
            }
            long cacheP = maxP;

            for (int i = 0; i < numM; i++) {
                int s = i + 2;
                long p = cacheP;

                p = p / (s - 1);
                p = p - arr[i];
                p *= s;

                cacheP = p;
                if (p > maxP) {
                    maxP = p;
                }
            }
            System.out.println(maxP);
        }
    }
}
