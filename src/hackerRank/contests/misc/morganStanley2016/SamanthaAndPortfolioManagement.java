package hackerRank.contests.misc.morganStanley2016;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chin on 06-Aug-16.
 */
public class SamanthaAndPortfolioManagement {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = in.nextInt();
        }

        int a = n * (n+1) / 2;
        BigRational e = new BigRational(BigInteger.valueOf(r[0]), BigInteger.valueOf(a));
        BigRational w = new BigRational(BigInteger.valueOf(1), BigInteger.valueOf(a));
        for (int i = 1; i < n; i++) {
            BigRational tmp1 = new BigRational(BigInteger.valueOf(i+1), BigInteger.valueOf(i));
            BigRational tmp2 = new BigRational(BigInteger.valueOf(r[i]), BigInteger.valueOf(1));
            w = tmp1.multiply(w);
            BigRational tmp3 = tmp2.multiply(w);
            e = e.add(tmp3);
        }

        System.out.println(e.getNumerator() + " " + e.getDenominator());
        System.out.println(1 + " " + a);
    }

    /**
     * A class that allows working with fractions
     * From http://stackoverflow.com/a/474577/1748450
     */
    public static class BigRational extends Number implements Comparable<BigRational>, Serializable {
        public final static BigRational ZERO = new BigRational(BigInteger.ZERO, BigInteger.ONE);
        private final static long serialVersionUID = 1099377265582986378L;

        private final BigInteger numerator, denominator;

        private BigRational(BigInteger numerator, BigInteger denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        private static BigRational canonical(BigInteger numerator, BigInteger denominator, boolean checkGcd) {
            if (denominator.signum() == 0) {
                throw new IllegalArgumentException("denominator is zero");
            }
            if (numerator.signum() == 0) {
                return ZERO;
            }
            if (denominator.signum() < 0) {
                numerator = numerator.negate();
                denominator = denominator.negate();
            }
            if (checkGcd) {
                BigInteger gcd = numerator.gcd(denominator);
                if (!gcd.equals(BigInteger.ONE)) {
                    numerator = numerator.divide(gcd);
                    denominator = denominator.divide(gcd);
                }
            }
            return new BigRational(numerator, denominator);
        }

        public static BigRational getInstance(BigInteger numerator, BigInteger denominator) {
            return canonical(numerator, denominator, true);
        }

        public static BigRational getInstance(long numerator, long denominator) {
            return canonical(new BigInteger("" + numerator), new BigInteger("" + denominator), true);
        }

        public static BigRational getInstance(String numerator, String denominator) {
            return canonical(new BigInteger(numerator), new BigInteger(denominator), true);
        }

        public static BigRational valueOf(String s) {
            Pattern p = Pattern.compile("(-?\\d+)(?:.(\\d+)?)?0*(?:e(-?\\d+))?");
            Matcher m = p.matcher(s);
            if (!m.matches()) {
                throw new IllegalArgumentException("Unknown format '" + s + "'");
            }

            // this translates 23.123e5 to 25,123 / 1000 * 10^5 = 2,512,300 / 1 (GCD)
            String whole = m.group(1);
            String decimal = m.group(2);
            String exponent = m.group(3);
            String n = whole;

            // 23.123 => 23123
            if (decimal != null) {
                n += decimal;
            }
            BigInteger numerator = new BigInteger(n);

            // exponent is an int because BigInteger.pow() takes an int argument
            // it gets more difficult if exponent needs to be outside {-2 billion,2 billion}
            int exp = exponent == null ? 0 : Integer.valueOf(exponent);
            int decimalPlaces = decimal == null ? 0 : decimal.length();
            exp -= decimalPlaces;
            BigInteger denominator;
            if (exp < 0) {
                denominator = BigInteger.TEN.pow(-exp);
            } else {
                numerator = numerator.multiply(BigInteger.TEN.pow(exp));
                denominator = BigInteger.ONE;
            }

            // done
            return canonical(numerator, denominator, true);
        }

        // Comparable
        public int compareTo(BigRational o) {
            // note: this is a bit of cheat, relying on BigInteger.compareTo() returning
            // -1, 0 or 1.  For the more general contract of compareTo(), you'd need to do
            // more checking
            if (numerator.signum() != o.numerator.signum()) {
                return numerator.signum() - o.numerator.signum();
            } else {
                // oddly BigInteger has gcd() but no lcm()
                BigInteger i1 = numerator.multiply(o.denominator);
                BigInteger i2 = o.numerator.multiply(denominator);
                return i1.compareTo(i2); // expensive!
            }
        }

        public BigRational add(BigRational o) {
            if (o.numerator.signum() == 0) {
                return this;
            } else if (numerator.signum() == 0) {
                return o;
            } else if (denominator.equals(o.denominator)) {
                return new BigRational(numerator.add(o.numerator), denominator);
            } else {
                return canonical(numerator.multiply(o.denominator).add(o.numerator.multiply(denominator)), denominator.multiply(o.denominator), true);
            }
        }

        public BigRational multiply(BigRational o) {
            if (numerator.signum() == 0 || o.numerator.signum( )== 0) {
                return ZERO;
            } else if (numerator.equals(o.denominator)) {
                return canonical(o.numerator, denominator, true);
            } else if (o.numerator.equals(denominator)) {
                return canonical(numerator, o.denominator, true);
            } else if (numerator.negate().equals(o.denominator)) {
                return canonical(o.numerator.negate(), denominator, true);
            } else if (o.numerator.negate().equals(denominator)) {
                return canonical(numerator.negate(), o.denominator, true);
            } else {
                return canonical(numerator.multiply(o.numerator), denominator.multiply(o.denominator), true);
            }
        }

        public BigInteger getNumerator() { return numerator; }
        public BigInteger getDenominator() { return denominator; }
        public boolean isInteger() { return numerator.signum() == 0 || denominator.equals(BigInteger.ONE); }
        public BigRational negate() { return new BigRational(numerator.negate(), denominator); }
        public BigRational invert() { return canonical(denominator, numerator, false); }
        public BigRational abs() { return numerator.signum() < 0 ? negate() : this; }
        public BigRational pow(int exp) { return canonical(numerator.pow(exp), denominator.pow(exp), true); }
        public BigRational subtract(BigRational o) { return add(o.negate()); }
        public BigRational divide(BigRational o) { return multiply(o.invert()); }
        public BigRational min(BigRational o) { return compareTo(o) <= 0 ? this : o; }
        public BigRational max(BigRational o) { return compareTo(o) >= 0 ? this : o; }

        public BigDecimal toBigDecimal(int scale, RoundingMode roundingMode) {
            return isInteger() ? new BigDecimal(numerator) : new BigDecimal(numerator).divide(new BigDecimal(denominator), scale, roundingMode);
        }

        // Number
        public int intValue() { return isInteger() ? numerator.intValue() : numerator.divide(denominator).intValue(); }
        public long longValue() { return isInteger() ? numerator.longValue() : numerator.divide(denominator).longValue(); }
        public float floatValue() { return (float)doubleValue(); }
        public double doubleValue() { return isInteger() ? numerator.doubleValue() : numerator.doubleValue() / denominator.doubleValue(); }

        @Override
        public String toString() { return isInteger() ? String.format("%,d", numerator) : String.format("%,d / %,d", numerator, denominator); }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BigRational that = (BigRational) o;

            if (denominator != null ? !denominator.equals(that.denominator) : that.denominator != null) return false;
            if (numerator != null ? !numerator.equals(that.numerator) : that.numerator != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = numerator != null ? numerator.hashCode() : 0;
            result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
            return result;
        }

        public static void dump(String name, BigRational r) {
            System.out.printf("%s = %s%n", name, r);
            System.out.printf("%s.negate() = %s%n", name, r.negate());
            System.out.printf("%s.invert() = %s%n", name, r.invert());
            System.out.printf("%s.intValue() = %,d%n", name, r.intValue());
            System.out.printf("%s.longValue() = %,d%n", name, r.longValue());
            System.out.printf("%s.floatValue() = %,f%n", name, r.floatValue());
            System.out.printf("%s.doubleValue() = %,f%n", name, r.doubleValue());
            System.out.println();
        }
    }
}
