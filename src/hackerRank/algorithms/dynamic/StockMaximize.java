package hackerRank.algorithms.dynamic;

import java.io.IOException;
import java.util.*;

/**
 * Created by Chin on 18-Jul-16.
 * There's nothing dynamic in here.
 */
public class StockMaximize {
    static class Pair {
        public int value;
        public int index;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int tx = 0; tx < t; tx++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            ArrayList<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int value = in.nextInt();
                Pair p = new Pair();
                p.value = value;
                p.index = i;
                list.add(p);
                arr[i] = value;
            }

            // sort descending in a stable way based on value
            Collections.sort(list, (o1, o2) -> Integer.compare(o2.value, o1.value));

            int list_idx = 0;
            int old_idx = -1;
            long sum = 0;
            boolean done = false;
            while (list_idx < n && !done) {
                int value = list.get(list_idx).value;
                if (list_idx < n - 1) {
                    // scroll through to the last value that is equal to the current one
                    if (list.get(list_idx + 1).value == value) {
                        list_idx++;
                        continue;
                    }
                }
                int new_index = list.get(list_idx).index;
                if (new_index < old_idx) {
                    list_idx++;
                    continue;
                }

                list_idx++;

                int start = old_idx + 1;
                for (int i = start; i < new_index; i++) {
                    sum += (value - arr[i]);
                }

                old_idx = new_index;

                if (new_index == n-1) {
                    System.out.println(sum);
                    done = true;
                }
            }
        }
    }
}
