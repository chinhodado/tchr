package hackerRank.hr9;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FairRations {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] % 2 != 0) {
                arr[i] += 1;
                arr[i+1] += 1;
                total += 2;
            }
        }

        boolean isGood = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 != 0) {
                isGood = false;
                break;
            }
        }

        if (isGood) {
            System.out.println(total);
        }
        else {
            System.out.println("NO");
        }
    }
}