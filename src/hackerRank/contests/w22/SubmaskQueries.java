package hackerRank.contests.w22;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tdo on 12-Aug-16.
 */
public class SubmaskQueries {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr_query_type = new int[m];
        int[] arr_x = new int[m];
        int[] arr_s = new int[m];
        ArrayList<Integer> query3_idx = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int query = in.nextInt();
            arr_query_type[i] = query;
            if (query == 1 || query == 2) {
                int x = in.nextInt();
                String s = in.next();
                arr_x[i] = x;
                arr_s[i] = Integer.parseInt(s, 2);
            }
            else if (query == 3) {
                String s = in.next();
                arr_s[i] = Integer.parseInt(s, 2);
                query3_idx.add(i);
            }
        }

        for (int i = 0; i < query3_idx.size(); i++) {
            int idx = query3_idx.get(i);
            int set = arr_s[idx];
            int result = 0;
            for (int j = idx - 1; j >= 0; j--) {
                int mask = arr_s[j];
                if ((mask & set) == set) {
                    if (arr_query_type[j] == 1) {
                        result = result ^ arr_x[j];
                        break;
                    }
                    else {
                        result = result ^ arr_x[j];
                    }
                }
            }
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}
