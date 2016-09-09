package hackerRank.contests.hack101.hack40;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by tdo on 23-Aug-16.
 * Note: not working
 */
public class SherlockAndTheDroppingBall {
    static class Line {
        int x1, y1, x2, y2;
        public Line(int x1, int y1, int x2, int y2) {
            if (y1 < y2) {
                this.x1 = x2;
                this.y1 = y2;
                this.x2 = x1;
                this.y2 = y1;
            }
            else {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        ArrayList<Line> lines = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            lines.add(new Line(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
        }

        lines.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return Integer.compare(o2.y2, o1.y2);
            }
        });

        for (int qx = 0; qx < q;  qx++) {
            int p_x = in.nextInt();
            int p_y = in.nextInt();
            for (int i = 0; i < lines.size(); i++) {
                Line line = lines.get(i);
                if (p_y < line.y2) {
                    continue;
                }
                int max_line_x = Math.max(line.x1, line.x2);
                int min_line_x = Math.min(line.x1, line.x2);
                if (p_x < min_line_x || p_x > max_line_x) {
                    continue;
                }

                p_x = line.x2;
                p_y = line.y2;
            }
            System.out.println(p_x);
        }
    }
}
