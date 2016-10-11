package hackerRank.contests.w23;

import java.util.Scanner;

/**
 * Created by Chin on 13-Sep-16.
 */
public class GearsOfWar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int nx = 0; nx < n; nx++) {
            int i = in.nextInt();
            System.out.println(i % 2 == 0? "Yes" : "No");
        }
    }
}
