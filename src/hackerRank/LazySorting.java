package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LazySorting {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i-1]) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            System.out.println("0");
            return;
        }

        Arrays.sort(arr);
        ArrayList<Integer> a = new ArrayList<>();
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i-1]) {
                count++;
                if (i == n - 1) {
                    a.add(count);
                }
            }
            else {
                a.add(count);
                count = 1;
            }
        }

        long numUniquePerm = getPerm(n);
        for (int i = 0; i < a.size(); i++) {
            numUniquePerm /= getPerm(a.get(i));
        }

        DecimalFormat formatter = new DecimalFormat("0.000000");
        System.out.println(formatter.format(numUniquePerm));
    }

    public static long getPerm(int n) {
        if (n == 0) return 1;
        long res = 1;

        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
