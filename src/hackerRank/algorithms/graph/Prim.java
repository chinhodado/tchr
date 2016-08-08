package hackerRank.algorithms.graph;

import java.util.*;

/**
 * Created by tdo on 08-Aug-16.
 * Prim using PriorityQueue - not optimal since using PriorityQueue's remove which is O(n)
 */
public class Prim {
    public static class Node {
        int id;
        int mstCost = Integer.MAX_VALUE;
        Set<Node> adjacents;
        public Node(int id) {
            this.id = id;
            adjacents = new HashSet<>();
        }
        public void addNeighbor(Node v) {
            if (!adjacents.contains(v)) {
                adjacents.add(v);
            }
        }
        public boolean isNeighbor(Node v) {
            return adjacents.contains(v);
        }
        @Override
        public int hashCode() {
            return this.id;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Node other = (Node) obj;
            return this.id == other.id;
        }
        @Override
        public String toString() {
            return id + "";
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] map = new int[n][n];

        for (int[] row : map) {
            Arrays.fill(row, -1);
        }

        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            arr[i] = node;
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int r = in.nextInt();
            map[x][y] = r;
            map[y][x] = r;
            arr[x].addNeighbor(arr[y]);
            arr[y].addNeighbor(arr[x]);
        }

        int s = in.nextInt() - 1;

        Queue<Node> queue = new PriorityQueue<>(8, (pos1, pos2) -> pos1.mstCost - pos2.mstCost);

        for (Node node : arr) {
            if (node.id == s) {
                node.mstCost = 0;
            }
            queue.add(node);
        }

        Set<Node> mst = new HashSet<>();
        // note that we're not interested in the actual MST here, we're just interested in the min cost,
        // hence no info about the actual edges is recorded.
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            mst.add(current);
            for (Node neighbor : current.adjacents) {
                if (!mst.contains(neighbor) && map[current.id][neighbor.id] < neighbor.mstCost) {
                    neighbor.mstCost = map[current.id][neighbor.id];
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        int totalCost = 0;
        for (Node node : mst) {
            totalCost += node.mstCost;
        }
        System.out.println(totalCost);
    }
}
