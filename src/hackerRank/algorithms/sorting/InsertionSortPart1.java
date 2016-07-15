package hackerRank.algorithms.sorting;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 14-Jul-16.
 */
public class InsertionSortPart1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int e = arr[arr.length - 1];

        boolean done = false;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > e) {
                arr[i + 1] = arr[i];
            }
            else {
                arr[i + 1] = e;
                done = true;
            }
            printArr(arr);
            System.out.println();

            if (done) break;
        }

        if (!done) {
            arr[0] = e;
            printArr(arr);
        }
    }

    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1? "" : " "));
        }
    }
}
