package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by tdo on 30-Jun-16.
 * Week of Code 21
 * The Letter N
 */
public class TheLetterN {
    static int[][] arr;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }

        long count = 0;
        for (int b = 0; b < n; b++) {
            for (int c = b + 1; c < n; c++) {
                int a_count = 0;
                int d_count = 0;
                for (int k = 0; k < n; k++) {
                    if (k == b || k == c) continue;

                    double bk = d(b, k);
                    double ck = d(c, k);
                    double bc = d(b, c);
                    double kbc = Math.acos((Math.pow(bk, 2) + Math.pow(bc, 2) - Math.pow(ck, 2)) / (2 * bk * bc));
                    double bck = Math.acos((Math.pow(ck, 2) + Math.pow(bc, 2) - Math.pow(bk, 2)) / (2 * ck * bc));

                    double bcx = arr[c][0] - arr[b][0];
                    double bcy = arr[c][1] - arr[b][1];
                    double bkx = arr[k][0] - arr[b][0];
                    double bky = arr[k][1] - arr[b][1];

                    double cross_z = bky * bcx - bkx * bcy;
                    boolean left_bc = cross_z > 0;
                    boolean right_bc = cross_z < 0;

                    if (right_bc && kbc > 0 && kbc <= Math.PI / 2){
                        a_count++;
                    }

                    if (left_bc && bck > 0 && bck <= Math.PI /2) {
                        d_count++;
                    }
                }
                count += a_count * d_count;
            }
        }

        System.out.println(count);
    }

    // distance between 2 points
    static double d(int a, int b) {
        return Math.sqrt(Math.pow(arr[a][0] - arr[b][0], 2) + Math.pow(arr[a][1] - arr[b][1], 2));
    }
}
