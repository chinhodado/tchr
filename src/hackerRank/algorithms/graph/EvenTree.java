package hackerRank.algorithms.graph;

import java.util.*;

/**
 * Created by Chin on 11-Aug-16.
 */
public class EvenTree {
    static ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();
    static Set<Integer> visited = new HashSet<>();
    static int n;
    static int cut_count;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            neighbors.get(x).add(y);
            neighbors.get(y).add(x);
        }

        dfs(0);
        System.out.println(cut_count);
    }

    // return the total number of nodes in the subtree where the current node is the root (including the current node)
    static int dfs(int current) {
        int total = 1; // initially 1 total node since we will always have the current node
        visited.add(current);
        for (int i : neighbors.get(current)) {
            if (!visited.contains(i)) {
                int nodes_in_subtree = dfs(i);
                if (nodes_in_subtree % 2 == 0) {
                    cut_count++; // cut the edge connecting the current node and the node i
                }
                else {
                    total += nodes_in_subtree;
                }
            }
        }
        return total;
    }
}
