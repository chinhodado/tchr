package hackerRank.contests.weekOfCode.w27;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Chin on 24-Dec-16.
 */
public class CoprimePaths {
    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            Node tmp = new Node(i, value);
            nodes[i] = tmp;
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            nodes[u].addNeighbor(nodes[v]);
            nodes[v].addNeighbor(nodes[u]);
        }

        StringBuilder sb = new StringBuilder();
        for (int qx = 0; qx < q; qx++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            List<Node> path = bfs(nodes[u],nodes[v]);
            long count = 0;
            for (int i = 0; i < path.size(); i++) {
                for (int j = i + 1; j < path.size(); j++) {
                    Node uNode = path.get(i);
                    Node vNode = path.get(j);
                    if (coprime(uNode.value, vNode.value)) {
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    static boolean coprime(long u, long v) {
        if (((u | v) & 1) == 0) return false;

        while ((u & 1) == 0) u >>= 1;
        if (u == 1) return true;

        do {
            while ((v & 1) == 0) v >>= 1;
            if (v == 1) return true;

            if (u > v) {
                long t = v;
                v = u;
                u = t;
            }
            v -= u;
        } while (v != 0);

        return false;
    }

    private static class Node {
        int id;
        int value;
        Set<Node> adjacents;
        public Node(int id, int value) {
            this.id = id;
            this.value = value;
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

    static List<Node> bfs(Node start, Node end) {
        Queue<Node> frontierNodes = new LinkedList<>();
        frontierNodes.add(start);
        Set<Node> exploredNodes = new HashSet<>();

        // maps a node to the previous node
        Map<Node, Node> prevMap = new HashMap<>();
        Node endNode = null;
        while (!frontierNodes.isEmpty() && endNode == null) {
            Node currentNode = frontierNodes.remove();
            exploredNodes.add(currentNode);
            for (Node neighbor : currentNode.adjacents) {
                if (!exploredNodes.contains(neighbor) && !frontierNodes.contains(neighbor)) {
                    frontierNodes.add(neighbor);
                    prevMap.put(neighbor, currentNode);
                    if (neighbor.id == end.id) {
                        endNode = neighbor;
                        break;
                    }
                }
            }
        }

        List<Node> toReturn = new ArrayList<>();
        Node current = endNode;
        while (true) {
            toReturn.add(current);
            Node prev = prevMap.get(current);
            if (prev == null) {
                break; // our start node
            }
            else {
                current = prev;
            }
        }

        return toReturn;
    }
}
