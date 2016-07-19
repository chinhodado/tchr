package hackerRank.algorithms.dynamic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chin on 18-Jul-16.
 */
public class RedJohnIsBack {
    static int[] cache;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        cache = new int[41];
        Arrays.fill(cache, -1);
        ArrayList<Integer> configs = new ArrayList<>();
        int max = -1;
        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int numConfig = getNumConfig(n);
            configs.add(numConfig);
            if (numConfig > max) max = numConfig;
        }

        int[] primes = generatePrimes(max + 1);
        for (int c : configs) {
            int count = 0;
            for (int prime : primes) {
                if (prime <= c) {
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count);
        }
    }

    static int getNumConfig(int n) {
        if (n <= 3) {
            return 1;
        }

        int tmp1 = cache[n-1];
        if (tmp1 == -1) {
            tmp1 = getNumConfig(n-1);
            cache[n-1] = tmp1;
        }

        int tmp4 = cache[n-4];
        if (tmp4 == -1) {
            tmp4 = getNumConfig(n-4);
            cache[n-4] = tmp4;
        }

        cache[n] = tmp1 + tmp4;
        return cache[n];
    }

    // Return primes less than limit
    static int[] generatePrimes(int limit) {
        final int numPrimes = countPrimesUpperBound(limit);
        ArrayList<Integer> primes = new ArrayList<>(numPrimes);
        boolean [] isComposite    = new boolean [limit];   // all false
        final int sqrtLimit       = (int)Math.sqrt(limit); // floor
        for (int i = 2; i <= sqrtLimit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (int j = i*i; j < limit; j += i) // `j+=i` can overflow
                    isComposite[j] = true;
            }
        }
        for (int i = sqrtLimit + 1; i < limit; i++)
            if (!isComposite[i])
                primes.add(i);
        return primes.stream().mapToInt(i -> i).toArray();
    }

    static int countPrimesUpperBound(int max) {
        return max > 1 ? (int)(1.25506 * max / Math.log((double)max)) : 0;
    }
}
