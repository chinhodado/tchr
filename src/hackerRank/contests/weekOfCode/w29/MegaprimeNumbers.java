package hackerRank.contests.weekOfCode.w29;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 23-Feb-17.
 */
public class MegaprimeNumbers {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        long first = in.nextLong();
        long last = in.nextLong();

        long count = 0;
        for (long i = first; i <= last; i++) {
            char[] arr = (i + "").toCharArray();
            boolean isGood = true;
            for (char c : arr) {
                if (c != '2' && c != '3' && c != '5' && c != '7') {
                    isGood = false;
                    break;
                }
            }

            if (isGood /*&& isPrime(i)*/) {
                count++;
//                System.out.println(i);
            }
        }

        System.out.println(count);
    }

    private static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }
}
