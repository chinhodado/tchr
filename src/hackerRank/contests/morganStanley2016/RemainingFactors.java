package hackerRank.contests.morganStanley2016;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Chin on 06-Aug-16.
 */
public class RemainingFactors {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        HashMap<Long, Long> map = primeFactors(n);
        long sum1 = 1, sum2 = 1;
        for (Long value : map.values()) {
            sum1 *= (2 * value + 1);
            sum2 *= (value + 1);
        }
        sum1 = (sum1 - 1) / 2;
        sum2 = sum2 - 1;
        System.out.println(sum1 - sum2);
    }

    public static HashMap<Long, Long> primeFactors(long n) {
        HashMap<Long, Long> f = new HashMap<>();
        for (long i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                if (!f.containsKey(i)) {
                    f.put(i, 1L);
                }
                else {
                    f.put(i, f.get(i) + 1);
                }
                n /= i;
            }
        }

        if (n > 1) {
            if (!f.containsKey(n)) {
                f.put(n, 1L);
            }
            else {
                // shouldn't happen, but just to be sure (lol)
                f.put(n, f.get(n) + 1);
            }
        }

        return f;
    }
}
