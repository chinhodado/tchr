package hackerRank.contests.worldCodeSprint.wcs7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Chin on 25-Sep-16.
 */
public class SummingPieces {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }

        long start = System.currentTimeMillis();
        BigInteger toMod = BigInteger.valueOf(1000000007);
        BigInteger[] coeff = new BigInteger[n];
        BigInteger two = BigInteger.valueOf(2);
        BigInteger common = two.modPow(BigInteger.valueOf(n-1), toMod)
                               .add(two.modPow(BigInteger.valueOf(n), toMod))
                               .mod(toMod);
        for (int i = 0; i <= (n-1)/2; i++) {
            BigInteger n_i_1 = BigInteger.valueOf(n-i-1);
            coeff[i] = common.subtract(two.modPow(n_i_1, toMod))
                             .mod(toMod)
                             .subtract(two.modPow(BigInteger.valueOf(i), toMod))
                             .mod(toMod);
        }

        long doneCoeff = System.currentTimeMillis();
        System.out.println(doneCoeff - start);

//        for (int i = (n-1)/2+1; i < n; i++) {
//            coeff[i] = coeff[n-i-1];
//        }

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < n/2; i++) {
            BigInteger toAdd = BigInteger.valueOf(arr[i] + arr[n-i-1])
                                         .mod(toMod).multiply(coeff[i]);
            toAdd = toAdd.mod(toMod);
            result = result.add(toAdd);
            result = result.mod(toMod);
        }

        if (n % 2 != 0) {
            BigInteger toAdd = BigInteger.valueOf(arr[(n-1)/2])
                                         .multiply(coeff[(n-1)/2]);
            toAdd = toAdd.mod(toMod);
            result = result.add(toAdd);
            result = result.mod(toMod);
        }

        System.out.println(result.toString());

        long done = System.currentTimeMillis();
        System.out.println(done - doneCoeff);
    }
}
