package hackerRank;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PointsOnALine {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int x1 = in.nextInt();
        int y1 = in.nextInt();

        int x2 = in.nextInt();
        int y2 = in.nextInt();

        boolean isVertical = false;
        double m = 0;
        if (x1 - x2 != 0) {
            m = (double)(y1-y2) / (x1-x2);
        }
        else {
            isVertical = true;
        }

        boolean isGood = true;
        for (int p = 2; p < n; p++){
            int x3 = in.nextInt();
            int y3 = in.nextInt();

            if (x1 - x3 == 0) {
                if (!isVertical) {
                    isGood = false;
                    break;
                }
            }
            else {
                double m2 = (double)(y1-y3) / (x1-x3);
                if (m != m2) {
                    isGood = false;
                    break;
                }
            }
        }

        if (isGood) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
