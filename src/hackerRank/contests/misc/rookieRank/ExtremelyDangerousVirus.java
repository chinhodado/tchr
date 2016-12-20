package hackerRank.contests.misc.rookieRank;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Chin on 26-Jul-16.
 */
public class ExtremelyDangerousVirus {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        long t = in.nextLong();
        int base = (a + b) / 2;
        long res = BigInteger.valueOf(base).modPow(BigInteger.valueOf(t), BigInteger.valueOf(1000000007)).longValue();
        System.out.println(res);
    }
}