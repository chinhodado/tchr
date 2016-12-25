package hackerRank.contests.weekOfCode.w27;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 21-Dec-16.
 */
public class HackonacciMatrixRotations {
    public static void main (String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] angles = new int[q];
        for (int i = 0; i < q; i++) {
            angles[i] = in.nextInt();
        }

        int[] lookUp = new int[] {1,0,0,0,0,1,1};
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = (i+1)*(j+1);
                int idx = ((k % 7)+6)%7;
                matrix[i][j] = lookUp[idx];
            }
        }

        int[][] matrix90 = cloneMatrix(matrix);
        rotate(matrix90);
        int rotate90diff = getDiff(matrix, matrix90);

        int[][] matrix180 = cloneMatrix(matrix90);
        rotate(matrix180);
        int rotate180diff = getDiff(matrix, matrix180);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < angles.length; i++) {
            int nRotate = (angles[i] / 90) % 4;
            if (nRotate == 0) {
                sb.append(0 + "\n");
            }
            else if (nRotate == 1 || nRotate == 3) {
                sb.append(rotate90diff).append("\n");
            }
            else {
                sb.append(rotate180diff).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static int[][] cloneMatrix(int[][] matrix) {
        int [][] myInt = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++) {
            int[] aMatrix = matrix[i];
            int   aLength = aMatrix.length;
            myInt[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
        }

        return myInt;
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }

    public static int getDiff(int[][] arr1, int[][] arr2) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
