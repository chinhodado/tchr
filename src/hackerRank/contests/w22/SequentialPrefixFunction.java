package hackerRank.contests.w22;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Chin on 13-Aug-16.
 * TODO: not fast enough, need something like a persistent data structure
 */
public class SequentialPrefixFunction {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> s = new ArrayList<>(200000);
        ArrayList<Integer> border = new ArrayList<>(200000);
        StringBuilder sb = new StringBuilder();
        int last_idx = 0; // last idx that we have calculated the border for

        for (int nx = 0; nx < n; nx++) {
            String type = in.next();
            if (type.equals("+")) {
                int x = in.nextInt();
                s.add(x);

                int max_border_length;
                if (s.size() == 1) {
                    border.add(0, -1);
                    last_idx = 0;
                    max_border_length = 0;
                }
                else {
                    int i = last_idx + 1, j = border.get(border.size() - 1);

                    while(j >= 0 && !s.get(i).equals(s.get(j+1))) j = border.get(j);
                    if (s.get(i).equals(s.get(j+1))) j++;
                    border.add(i++, j);

                    last_idx = i - 1; // = n-1
                    max_border_length = j + 1;
                }

                sb.append(max_border_length).append("\n");
            }
            else {
                if (s.size() > 0) {
                    s.remove(s.size() - 1);
                    border.remove(border.size() - 1);
                    last_idx--;
                }
                int max_border_length = border.size() == 0? 0 : (border.get(border.size() - 1) + 1);
                sb.append(max_border_length).append("\n");
            }
        }

        System.out.println(sb);
    }
}