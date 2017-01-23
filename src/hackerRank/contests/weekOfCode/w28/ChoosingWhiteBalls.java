package hackerRank.contests.weekOfCode.w28;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Correct but not fast enough
 * Created by Chin on 13-Jan-17.
 */
public class ChoosingWhiteBalls {
    static class Node {
        String s;
        double expected;
        Node parent;
        List<Node> children = new ArrayList<>();
        public Node(String s) {
            this.s = s;
        }
    }

    static Map<String, Double> cache = new HashMap<>(10000);

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();

        Node root = new Node(s);
        double expected = getExpected(root, k);
        System.out.println(expected);
    }

    static double getExpected(Node node, int toRemove) {
        if (cache.containsKey(node.s)) {
            return cache.get(node.s);
        }

        int n = node.s.length();
        if (toRemove == 1) {
            double sum = 0;
            int lastIndex = (n-1) / 2;
            for (int i = 0; i < lastIndex; i++) {
                if (node.s.charAt(i) == 'W' || node.s.charAt(n-i-1) == 'W') {
                    sum++;
                }
            }
            sum *= 2;

            if (node.s.charAt(lastIndex) == 'W' || node.s.charAt(n-lastIndex-1) == 'W') {
                if (n % 2 == 0) {
                    sum += 2;
                }
                else {
                    sum++;
                }
            }

            sum /= n;
            node.expected = sum;
            cache.put(node.s, sum);
            return sum;
        }

        for (int i = 0; i < node.s.length(); i++) {
            StringBuilder sb = new StringBuilder(node.s);
            sb.deleteCharAt(i);
            Node newChild = new Node(sb.toString());
            newChild.parent = node;
            node.children.add(newChild);
        }

        double sum = 0;
        for (int i = 0; i < n; i++) {
            double e1 = getExpected(node.children.get(i), toRemove - 1);
            double e2 = getExpected(node.children.get(n-i-1), toRemove - 1);
            if (node.s.charAt(i) == 'W') {
                e1 += 1;
            }
            if (node.s.charAt(n-i-1) == 'W') {
                e2 += 1;
            }
            sum += Math.max(e1, e2);
        }

        sum /= n;

        node.expected = sum;
        cache.put(node.s, sum);
        return sum;
    }
}
