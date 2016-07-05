package hackerRank.hack38;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class EasyGCD {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr;

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int gcd = gcd(arr);
        int m = 0;

        for (int i = 2; i <= k; i++) {
            if (gcd % i == 0) {
                m = i;
                break;
            }
        }

        int res;

        if (m == 0) {
            res = 0;
        }
        else {
            int tmp = k / m;
            res = m * tmp;
        }

        System.out.println(res);
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static int gcd(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }
}
