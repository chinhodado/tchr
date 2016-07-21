import java.io.*;
import java.util.*;

public class JimAndTheOrders {
    static class Pair {
        public int value;
        public int index;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] time = new int[n];
        int[] duration = new int[n];
        ArrayList<Pair> pairs = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt();
            duration[i] = in.nextInt();
            Pair p = new Pair();
            p.value = time[i] + duration[i];
            p.index = i + 1;
            pairs.add(p);
        }

        Collections.sort(pairs, (o1, o2) -> Integer.compare(o1.value, o2.value));

        for (int i = 0; i < pairs.size(); i++) {
            System.out.print(pairs.get(i).index + (i == pairs.size() - 1? "" : " "));
        }
        System.out.println();
    }
}
