package hackerRank.contests.weekOfCode.w27;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 19-Dec-16.
 */
public class DrawingBook {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int front = (int)Math.ceil((p-1) / 2.0);
        int rear = (n%2==0)? (int)Math.ceil((n-p)/2.0) : (int)Math.ceil((n-p-1)/2.0);
        int res = Math.min(front, rear);
        System.out.println(res);
    }
}
