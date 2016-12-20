package hackerRank.contests.weekOfCode.w22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by tdo on 11-Aug-16.
 * TODO: not sure about logic, lots of tests fail
 */
public class NumberOfSequences {
    static Set<Integer> primes;
    static boolean [] isComposite;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n+1];
        Set<Integer> toProcess = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
            if (arr[i] == -1) {
                toProcess.add(i);
            }
        }

        primes = new HashSet<>(generatePrimes(n+1));

        // If there is a position k with a[k]==-1, but at the same time there are some multiples of k,
        // say m that a[m]!=-1, then the value of nice a[k] is fixed, it equals to a[m]%k.
        for (Iterator<Integer> it = toProcess.iterator(); it.hasNext();) {
            int i = it.next();
            int tmp = i * 2;
            while (tmp <= n) {
                if (arr[tmp] != -1) {
                    it.remove();
                    arr[i] = -2; //fix i
                    break;
                }
                tmp += i;
            }
        }

        HashMap<Integer, Integer> maxPrimePowers = new HashMap<>();

        for (Iterator<Integer> it = toProcess.iterator(); it.hasNext();) {
            int i = it.next();
            if (isComposite[i]) {
                int k = isPrimePower(i);
                if (k != -1) {
                    if (maxPrimePowers.containsKey(k)) {
                        maxPrimePowers.put(k, Math.max(i, maxPrimePowers.get(k)));
                    }
                    else {
                        maxPrimePowers.put(k, i);
                    }
                }
                else {
                    // product of 2 coprimes
                    it.remove();
                }
            }
        }

        ArrayList<Integer> powers = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : maxPrimePowers.entrySet()) {
            int basePrime = entry.getKey();
            int maxPow = entry.getValue();
            int minPow = maxPow;
            while (minPow > 1 && toProcess.contains(minPow)) {
                toProcess.remove(minPow);
                minPow /= basePrime;
            }
            powers.add(maxPow / minPow);
        }

        BigInteger count = BigInteger.valueOf(1);
        for (int i : toProcess) {
            count = count.multiply(BigInteger.valueOf(i));
        }
        for (int i : powers) {
            count = count.multiply(BigInteger.valueOf(i));
        }
        System.out.println(count.mod(BigInteger.valueOf(1000000007)));
    }

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    private static boolean relativelyPrime(int a, int b) {
        return gcd(a,b) == 1;
    }

    static double precision = 0.00000001;
    private static int isPrimePower(int n) {
        if (n <= 3) return -1;
        for (int i = log2(n) + 1; i >= 2; i--) {
            double tmp = Math.pow(n, 1.0/i);
            if (Math.abs(tmp - Math.round(tmp)) < precision) {
                // is integer
                int k = (int)Math.round(tmp);
                if (primes.contains(k)) {
                    return k;
                }
            }
        }
        return -1;
    }

    public static int log2(int n){
        if(n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    static ArrayList<Integer> generatePrimes(int limit) {
        final int numPrimes = countPrimesUpperBound(limit);
        ArrayList<Integer> primes = new ArrayList<>(numPrimes);
        isComposite    = new boolean [limit];   // all false
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
        return primes;
    }

    static int countPrimesUpperBound(int max) {
        return max > 1 ? (int)(1.25506 * max / Math.log((double)max)) : 0;
    }
}