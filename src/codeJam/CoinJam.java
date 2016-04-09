package codeJam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinJam {
    static List<Long> primeList = new ArrayList<Long>();
    public static void main(String[] args) {
        try {
            // Create output file
            FileWriter fstream = new FileWriter("out.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            // Open source file
            FileInputStream ifstream = new FileInputStream("in.txt");

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(ifstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int numberOfCase = Integer.parseInt(br.readLine());

            int N = 16;
            int J = 50;

            Set<Long> primes = new HashSet<Long>();

            long limit = (long) Math.pow(10, N/2);
            for (long i = 2; i <= limit; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                    primeList.add(i);
                }
            }
            System.out.println("Done finding primes");

            for (int caseN = 1; caseN <= numberOfCase; caseN++) {
                String[] s = br.readLine().split("\\s+");
                int jCount = 0;

                String initial = "1000000000000001";
                long iniNum = Integer.parseInt(initial, 2);
                long num = iniNum;
                out.write("Case #" + caseN + "\n");
                while (num <= limit) {
                    num += 2;
                    String numString = Long.toString(num, 2);
                    long[] divisors = new long[9];
                    boolean isJam = false;
                    for (int base = 2; base <= 10; base++) {
                        long interpreted = Long.parseLong(numString, base);
                        if (isPrime(interpreted)) {
                            break;
                        }
                        else {
                            if (base == 10) {
                                isJam = true;
                                jCount++;
                            }
                            for (int k = 0; k < primeList.size(); k++) {
                                long div = primeList.get(k);
                                if (interpreted % div == 0) {
                                    divisors[base - 2] = div;
                                    break;
                                }
                            }
                        }
                    }

                    if (isJam) {
                        System.out.println(numString + " " + Arrays.toString(divisors));
                        if (jCount == J) {
                            break;
                        }
                    }
                }
            }

            // Close the input stream
            in.close();

            // Close the output stream
            out.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static boolean isPrime(long n) {
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
