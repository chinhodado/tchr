package hackerRank.contests.rookieRank;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 26-Jul-16.
 */
public class MagicSquareForming {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int[][] magicSquares = new int[][] {
            new int[] {8,1,6,3,5,7,4,9,2},
            new int[] {6,1,8,7,5,3,2,9,4},
            new int[] {4,3,8,9,5,1,2,7,6},
            new int[] {2,7,6,9,5,1,4,3,8},
            new int[] {2,9,4,7,5,3,6,1,8},
            new int[] {4,9,2,3,5,7,8,1,6},
            new int[] {6,7,2,1,5,9,8,3,4},
            new int[] {8,3,4,1,5,9,6,7,2}
        };
        int[] arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = in.nextInt();
        }

        int min = Integer.MAX_VALUE;

        for (int[] square : magicSquares) {
            int diff = 0;
            for (int i = 0; i < 9; i++) {
                diff += Math.abs(square[i] - arr[i]);
            }
            min = Math.min(min, diff);
        }
        System.out.println(min);
    }
}
