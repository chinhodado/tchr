package hackerRank.contests.weekOfCode.w28;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Correct but not fast enough
 * Created by Chin on 14-Jan-17.
 */
public class SuffixRotation {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        long start = System.currentTimeMillis();
        for (int gx = 0; gx < g; gx++) {
            String s = in.next();
            Node initial = new Node(s, 0);
            int cost = getMinCost(initial, 0);
            System.out.println(cost);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end-start));
        System.out.println(count);
    }

    private static class Node {
        String s;
        int cost;
        public Node(String s, int cost) {
            this.s = s;
            this.cost = cost;
        }
    }

    static long count = 0;
    static Map<String, Integer> cache = new HashMap<>();
    private static int getMinCost(Node node, int idx) {
        if (cache.containsKey(node.s.substring(idx))) {
            int cost = cache.get(node.s.substring(idx));
            if (node.cost >= cost) {
                return cost;
            }
        }
        count++;
        char[] chars = node.s.toCharArray();
        boolean isSorted = true;
        for (int i = idx; i < node.s.length()-1; i++) {
            if (chars[i] > chars[i+1]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return node.cost;
        }

        List<Node> children = getChildren(node, idx);

        int minCost = Integer.MAX_VALUE;
        for (Node child : children) {
            int cost = getMinCost(child, idx+1);
            minCost = Math.min(cost, minCost);
        }

        cache.put(node.s.substring(idx), minCost);

        return minCost;
    }

    private static List<Node> getChildren(Node node, int idx) {
        List<Node> list = new ArrayList<>();
        String prefix = node.s.substring(0, idx);
        String suffix = node.s.substring(idx);
        char[] arr = suffix.toCharArray();
        char minChar = Character.MAX_VALUE;
        for (char c : arr) {
            minChar = c < minChar ? c : minChar;
        }

        Set<String> rotatedSet = new HashSet<>();
        for (int i = 0; i < suffix.length(); i++) {
            if (suffix.charAt(i) != minChar) {
                continue;
            }
            String rotated = suffix.substring(i) + suffix.substring(0, i);
            rotatedSet.add(rotated);
        }

        for (String rotated : rotatedSet) {
            int cost = node.cost;
            if (!rotated.equals(suffix)) {
                cost++;
            }
            Node newNode = new Node(prefix+rotated, cost);
            list.add(newNode);
        }

        return list;
    }
}
