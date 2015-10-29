package topCoder;

import java.util.Arrays;

public class AB {
    public static void main (String args[]) {
        System.out.println(createString(50, 150));
    }

    /**
     *
     * @param N length of string
     * @param K number of pairs
     * @return
     */
    public static String createString(int N, int K) {
        // let na and nb be number of a's and b's in the string respectively
        // cauchy: na + nb = N >= 2 * sqrt(na * nb)
        //       =>       N**2 >= 4 * na * nb
        //       =>    na * nb <= (N**2) / 4
        // on the other hand, max number of pairs is na * nb when na = nb = N/2, and all a's are before all b's
        // so K <= (N ** 2) / 4;
        if (K > Math.pow(N, 2) / 4) {
            return "";
        }

        if (K == 0) {
            char[] chars = new char[N];
            Arrays.fill(chars, 'B');
            return new String(chars);
        }

        int na = 0;
        while (true) {
            if (na * (N - na) >= K) {
                break;
            }
            na++;
        }
        na--;

        int r = K - ((na * (N - na)));
        char[] chars = new char[N];
        Arrays.fill(chars, 'B');
        for (int i = 0; i < na; i++) {
            chars[i] = 'A';
        }
        chars[N - 1 - r - na] = 'A';
        String s = new String(chars);

        return s;
    }
}
