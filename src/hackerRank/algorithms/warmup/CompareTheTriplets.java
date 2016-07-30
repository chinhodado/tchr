package hackerRank.algorithms.warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chin on 30-Jul-16.
 */
public class CompareTheTriplets {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int a_arr[] = new int[3];
        int b_arr[] = new int[3];

        for (int i = 0; i < 3; i++) {
            a_arr[i] = in.nextInt();
        }

        for (int i = 0; i < 3; i++) {
            b_arr[i] = in.nextInt();
        }

        int a_point = 0, b_point = 0;
        for (int i = 0; i < 3; i++) {
            if (a_arr[i] > b_arr[i]) a_point++;
            else if (a_arr[i] < b_arr[i]) b_point++;
        }

        System.out.println(a_point + " " + b_point);
    }
}
