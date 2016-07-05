package hackerRank.wcs4;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  World CodeSprint #4
 *  Equal Stacks
 */
public class EqualStack {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n[] = new int[3];

        for (int i = 0; i < 3; i++) {
            n[i] = in.nextInt();
        }

        int arr[][] = new int[3][];
        long heights[] = new long[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = new int[n[i]];
            for (int j = 0; j < n[i]; j++) {
                arr[i][j] = in.nextInt();
                heights[i] += arr[i][j];
            }
        }

        int index[] = new int[3];

        // ugly code, sorry
        while (!(heights[0] == heights[1] && heights[1] == heights[2])) {
            long max = heights[0];
            if (heights[1] > heights[0]) max = heights[1];
            if (heights[2] > heights[1]) max = heights[2];

            int maxIndex = -1;
            if (max == heights[0]) maxIndex = 0;
            else if (max == heights[1]) maxIndex = 1;
            else maxIndex = 2;

            int top = arr[maxIndex][index[maxIndex]];
            index[maxIndex]++;
            heights[maxIndex] -= top;
        }

        System.out.println(heights[0]);
    }
}
