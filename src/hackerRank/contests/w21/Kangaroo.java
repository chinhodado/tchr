package hackerRank.contests.w21;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Kangaroo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        int x12 = x1 - x2;
        int v21 = v2 - v1;
        if (x12 * v21 > 0 && x12 % v21 == 0) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
