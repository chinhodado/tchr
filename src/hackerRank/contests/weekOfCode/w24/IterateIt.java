package hackerRank.contests.weekOfCode.w24;

import java.util.*;

/**
 * Created by Chin on 16-Oct-16.
 */
public class IterateIt {
//    static int gcd(int x, int y) {
//        if (x > y) {
//            int tmp = x;
//            x = y;
//            y = tmp;
//        }
//        while (x > 0) {
//            int z = x;
//            x = y%x;
//            y = z;
//        }
//        return y;
//    }

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        ArrayList<Integer> start = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            start.add(in.nextInt());
        }

        Set<Integer> step1s = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!start.get(i).equals(start.get(j))) {
                    step1s.add(Math.abs(start.get(i) - start.get(j)));
                }
            }
        }

        start.clear();
        start.addAll(step1s);

        N = start.size();
        if (N == 0) {
            System.out.println(1);
            return;
        }

        int g = start.get(0);
        for (int i = 1; i < N; i++) {
            g = gcd(g, start.get(i));
        }
        for (int i = 0; i < N; i++) {
            start.set(i, start.get(i) / g);
        }

        int K = 32, M = 200000;
        List<Integer> live = new ArrayList<>(Collections.nCopies(M / K, 0));
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int idx = (start.get(j) - start.get(i) - 1) / K;
                live.set(idx, live.get(idx) | (1 << ((start.get(j) - start.get(i) - 1) % K)));
            }
        }

        int t = 2, m = start.get(N - 1) - start.get(0);

        while (m > 0 && (live.get(0) & 1) == 0) {
            List<Integer> liveN = new ArrayList<>(Collections.nCopies(M / K, 0));
            boolean b = false;
            int m2 = m;

            for (int i = 0; i < m; i++) {
                if (((live.get(i / K) >> (i%K)) & 1) != 0) {
                    if (!b) {
                        m2 = m - (i + 1);
                    }
                    b = true;
                    if (i%K < 31) {
                        for (int j = i / K; j < M / K; j++) {
                            int idx = j - i / K;
                            liveN.set(idx, liveN.get(idx) | (live.get(j) >> (i%K + 1)));
                        }
                    }
                    for (int j = i / K + 1; j < M / K; j++) {
                        int idx = j - i / K - 1;
                        liveN.set(idx, liveN.get(idx) | (live.get(j) << (K - 1 - i%K)));
                    }
                }
            }

            live = liveN;
            m = m2;
            t++;
        }

        System.out.println(t + m);
    }
}
