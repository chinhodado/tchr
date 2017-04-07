package hackerRank.contests.weekOfCode.w30;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Chin on 17-Mar-17.
 */
public class RangeModularQuery {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Map<Quad, Integer> cache = new HashMap<>();
        for (int qx = 0; qx < q; qx++) {
            int count = 0;
            int l = in.nextInt();
            int r = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            Quad quad = new Quad(l, r, x, y);
            if (cache.containsKey(quad)) {
                System.out.println(cache.get(quad));
                continue;
            }
            for (int i = l; i <= r; i++) {
                if (arr[i] % x == y) {
                    count++;
                }
            }
            cache.put(quad, count);
            System.out.println(count);
        }
    }

    private static class Quad {
        int l, r, x, y;

        Quad(int l, int r, int x, int y) {
            this.l = l;
            this.r = r;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Quad quad = (Quad) o;

            if (l != quad.l) return false;
            if (r != quad.r) return false;
            if (x != quad.x) return false;
            return y == quad.y;
        }

        @Override
        public int hashCode() {
            int result = l;
            result = 31 * result + r;
            result = 31 * result + x;
            result = 31 * result + y;
            return result;
        }
    }
}
