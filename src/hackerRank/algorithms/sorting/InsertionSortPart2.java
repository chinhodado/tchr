package hackerRank.algorithms.sorting;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 14-Jul-16.
 */
public class InsertionSortPart2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 1; i < n; i++) {
            int e = arr[i];
            boolean done = false;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > e) {
                    arr[j + 1] = arr[j];
                }
                else {
                    arr[j + 1] = e;
                    done = true;
                }

                if (done) break;
            }

            if (!done) {
                arr[0] = e;
            }

            printArr(arr);
            System.out.println();
        }
    }

    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1? "" : " "));
        }
    }
}
