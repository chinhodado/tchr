package hackerRank.contests.weekOfCode.w29;

import java.util.Scanner;

/**
 * Created by Chin on 23-Feb-17.
 */
public class ACircleAndASquare {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int x_circle = in.nextInt();
        int y_circle = in.nextInt();
        int r_circle = in.nextInt();
        int x1 = in.nextInt(); //b
        int y1 = in.nextInt();
        int x3 = in.nextInt(); //d
        int y3 = in.nextInt();

        int[][] arr = new int[h][w];
        double x2 = (x1+x3+y1-y3)/2.0;
        double y2 = (y1+y3+x3-x1)/2.0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (isInCircle(j, i, x_circle, y_circle, r_circle)) {
                    arr[i][j] = 1;
                    continue;
                }

                if (isInSquare(x2, y2, x1, y1, x3, y3, j, i)) {
                    arr[i][j] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j] == 0? "." : "#");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

//    static double distance (int x1, int y1, int x2, int y2) {
//        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
//    }

    static boolean isInCircle(int xp, int yp, int x_circle, int y_circle, int r_circle) {
        return (int)Math.pow(xp-x_circle, 2) + (int)Math.pow(yp-y_circle, 2) <= (int)Math.pow(r_circle, 2);
    }

    // A(x1,y1), B(x2,y2), D(x3,y3)
    static boolean isInSquare(double xa, double ya, int xb, int yb, int xd, int yd, int xm, int ym) {
        double am_ab = (xm-xa)*(xb-xa) + (ym-ya)*(yb-ya);
        if (am_ab < 0) return false;

        double ab_ab = (xb-xa)*(xb-xa) + (yb-ya)*(yb-ya);
        if (ab_ab < am_ab) return false;

        double am_ad = (xm-xa)*(xd-xa) + (ym-ya)*(yd-ya);
        if (am_ad < 0) return false;

        double ad_ad = (xd-xa)*(xd-xa) + (yd-ya)*(yd-ya);
        if (ad_ad < am_ad) return false;

        return true;
    }
}
