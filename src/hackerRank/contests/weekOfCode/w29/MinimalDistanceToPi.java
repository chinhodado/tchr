package hackerRank.contests.weekOfCode.w29;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Chin on 24-Feb-17.
 */
public class MinimalDistanceToPi {
    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
//        long min = in.nextLong();
//        long max = in.nextLong();
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            long min = randInt(rnd, 1, 999999999);
            long max = min + randInt(rnd, 1, 999999999);
            Rational bestFast = getBestApprox(max);
            Rational a;
            Rational bestBruteForce = getBestApproxBruteForce(min, max);
            if (bestFast.denominator() >= min) {
//                System.out.println(bestFast);
                a = bestFast;
            }
            else {
                a = bestBruteForce;
            }
            if (!a.equals(bestBruteForce)) {
                throw new Error("min is " + min + ", max is " + max + ", a is " + a + ", bestBruteForce is " + bestBruteForce);
            }
        }
    }

    static int randInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Rational getBestApproxBruteForce(long min, long max) {
        long a = 0, b = 0;
        double minDiff = Double.MAX_VALUE;
        for (long i = min; i <= max; i++) {
            double res = i * Math.PI;
            double u = Math.ceil(res) - res;
            double d = res - Math.floor(res);
            double tmp_a = d <= u? Math.floor(res) : Math.ceil(res);
            double newDiff = Math.abs(tmp_a / i - Math.PI);
            if (newDiff < minDiff) {
                minDiff = newDiff;
                a = (long)tmp_a;
                b = i;
            }
        }
        return new Rational(a, b);
    }

    public static Rational getBestApprox(long max) {
        BigDecimal x = new BigDecimal("3.1415926535897932384626433832795028841971693993751");
//        double epsilon = 1E-15;
        Rational left  = new Rational(0, 1);
        Rational right = new Rational(1, 0);
        Rational best = left;
        Rational lastBest = best;
        BigDecimal bestError = x.abs();
//        System.out.println(best + " = " + best.toBigDecimal() + ", error = " + bestError);

        // do Stern-Brocot binary search
//        while(bestError > epsilon) {
        while (best.denominator() <= max) {
            // compute next possible rational approximation
            Rational mediant = Rational.mediant(left, right);
            if (x.compareTo(mediant.toBigDecimal()) < 0) {
                right = mediant; // go left
            }
            else {
                left = mediant; // go right
            }

            // check if better and update champion
            BigDecimal error = mediant.toBigDecimal().subtract(x).abs();
            if (error.compareTo(bestError) < 0) {
                lastBest = best;
                best = mediant;
                bestError = error;
//                System.out.println("best = " + best + "("+ best.toBigDecimal() + "), error = " + bestError);
            }
        }
//        System.out.println();
        return lastBest;
    }

    public static class Rational implements Comparable<Rational> {
        private static Rational zero = new Rational(0, 1);

        private long num;   // the numerator
        private long den;   // the denominator

        // create and initialize a new Rational object
        public Rational(long numerator, long denominator) {

            // deal with x/0
            //if (denominator == 0) {
            //   throw new RuntimeException("Denominator is zero");
            //}

            // reduce fraction
            long g = gcd(numerator, denominator);
            num = numerator / g;
            den = denominator / g;

            // only needed for negative numbers
            if (den < 0) {
                den = -den;
                num = -num;
            }
        }

        // return the numerator and denominator of (this)
        public long numerator() {
            return num;
        }

        public long denominator() {
            return den;
        }

        // return double precision representation of (this)
//        public double toDouble() {
//            return (double) num / den;
//        }

        public BigDecimal toBigDecimal() {
            return BigDecimal.valueOf(num).divide(BigDecimal.valueOf(den), 100, BigDecimal.ROUND_UP);
        }

        // return string representation of (this)
        public String toString() {
            if (den == 1) return num + "";
            else return num + "/" + den;
        }

        // return { -1, 0, +1 } if a < b, a = b, or a > b
        public int compareTo(Rational b) {
            Rational a = this;
            long lhs = a.num * b.den;
            long rhs = a.den * b.num;
            if (lhs < rhs) return -1;
            if (lhs > rhs) return +1;
            return 0;
        }

        // is this Rational object equal to y?
        public boolean equals(Object y) {
            if (y == null) return false;
            if (y.getClass() != this.getClass()) return false;
            Rational b = (Rational) y;
            return compareTo(b) == 0;
        }

        // hashCode consistent with equals() and compareTo()
        public int hashCode() {
            return this.toString().hashCode();
        }

        // create and return a new rational (r.num + s.num) / (r.den + s.den)
        public static Rational mediant(Rational r, Rational s) {
            return new Rational(r.num + s.num, r.den + s.den);
        }

        // return gcd(|m|, |n|)
        private static long gcd(long m, long n) {
            if (m < 0) m = -m;
            if (n < 0) n = -n;
            if (0 == n) return m;
            else return gcd(n, m % n);
        }

        // return lcm(|m|, |n|)
        private static long lcm(long m, long n) {
            if (m < 0) m = -m;
            if (n < 0) n = -n;
            return m * (n / gcd(m, n));    // parentheses important to avoid overflow
        }

        // return a * b, staving off overflow as much as possible by cross-cancellation
        public Rational times(Rational b) {
            Rational a = this;

            // reduce p1/q2 and p2/q1, then multiply, where a = p1/q1 and b = p2/q2
            Rational c = new Rational(a.num, b.den);
            Rational d = new Rational(b.num, a.den);
            return new Rational(c.num * d.num, c.den * d.den);
        }

        // return a + b, staving off overflow
        public Rational plus(Rational b) {
            Rational a = this;

            // special cases
            if (a.compareTo(zero) == 0) return b;
            if (b.compareTo(zero) == 0) return a;

            // Find gcd of numerators and denominators
            long f = gcd(a.num, b.num);
            long g = gcd(a.den, b.den);

            // add cross-product terms for numerator
            Rational s = new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
                    lcm(a.den, b.den));

            // multiply back in
            s.num *= f;
            return s;
        }

        // return -a
        public Rational negate() {
            return new Rational(-num, den);
        }

        // return a - b
        public Rational minus(Rational b) {
            Rational a = this;
            return a.plus(b.negate());
        }

        public Rational reciprocal() {
            return new Rational(den, num);
        }

        // return a / b
        public Rational divides(Rational b) {
            Rational a = this;
            return a.times(b.reciprocal());
        }
    }
}
