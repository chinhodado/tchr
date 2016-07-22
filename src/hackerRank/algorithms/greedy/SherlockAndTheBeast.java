package hackerRank.algorithms.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 22-Jul-16.
 */
public class SherlockAndTheBeast {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int count5 = n - (n % 3);
            
            int count3 = 0;
            boolean isGood = false;
            while (count5 >= 0) {
                count3 = n - count5;
                if (count3 % 5 == 0) {
                    isGood = true;
                    break;
                }
                else {
                    count5 -= 3;
                }
            }
            
            if (isGood) {
                for (int i = 0; i < count5; i++)
                    System.out.print("5");
                
                for (int i = 0; i < count3; i++)
                    System.out.print("3");
                
                System.out.println();
            }
            else {
                System.out.println(-1);
            }
        }        
    }
}
