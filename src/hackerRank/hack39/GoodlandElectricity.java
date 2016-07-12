package hackerRank.hack39;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 12-Jul-16.
 * 101 Hack 39
 * Goodland Electricity
 */
public class GoodlandElectricity {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] light = new int[n];
        int lightCount = 0;
        for (int i = 0; i < n; i++) {
            int right = i + k -1;

            if (right >= n) right = n - 1;

            boolean foundJ = false;
            for (int j = right; j >= i; j--) {
                if (arr[j] == 1) {
                    light[j] = 1;
                    lightCount++;
                    i = j + k - 1;
                    foundJ = true;
                    break;
                }
            }

            if (!foundJ) {
                int left = i - k + 1;
                if (left < 0) left = 0;

                for (int j = i - 1; j >= left; j--) {
                    if (light[j] == 1) break;

                    if (arr[j] == 1) {
                        light[j] = 1;
                        lightCount++;
                        i = j + k - 1;
                        foundJ = true;
                        break;
                    }
                }
            }

            if (!foundJ) {
                System.out.print(-1);
                return;
            }
        }

        System.out.print(lightCount);
    }
}
