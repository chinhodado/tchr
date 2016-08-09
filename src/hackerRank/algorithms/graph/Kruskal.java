package hackerRank.algorithms.graph;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Chin on 08-Aug-16.
 * Implementation of Kruskal's algorithm using a PriorityQueue for the edges
 * and Union-Find Algorithm for detecting cycle (however the Union-Find algorithm
 * is naive and runs in O(n). Can be improved to O(logn) using union by rank.
 */
public class Kruskal {
    static int[] parent;

    public static class Node {
        int id;
        public Node(int id) {
            this.id = id;
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
    
    static class Edge {
        Node x, y;
        int weight;
        public Edge(Node x, Node y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        parent = new int[n];

        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            arr[i] = node;
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int r = in.nextInt();
            edges[i] = new Edge(arr[x], arr[y], r);
        }

        int s = in.nextInt() - 1;

        Queue<Edge> queue = new PriorityQueue<>(8, (edge1, edge2) -> edge1.weight - edge2.weight);
        Collections.addAll(queue, edges);

        Set<Edge> mst = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge current = queue.remove();
            mst.add(current);
            if (!isAcyclic(mst)) {
                mst.remove(current);
            }
        }

        int totalCost = 0;
        for (Edge edge : mst) {
            totalCost += edge.weight;
        }
        System.out.println(totalCost);
    }

    // A utility function to find the subset of an element i
    static int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    // A utility function to do union of two subsets
    static void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    // The main function to check whether a given graph contains cycle or not
    static boolean isAcyclic(Set<Edge> edges) {
        // Initialize all subsets as single element sets
        Arrays.fill(parent, -1);

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (Edge edge : edges) {
            int x = find(parent, edge.x.id);
            int y = find(parent, edge.y.id);

            if (x == y)
                return false;

            union(parent, x, y);
        }
        return true;
    }
}
