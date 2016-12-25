package hackerRank.algorithms.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Bfs {
    private static class Node {
        int id;
        int distance = -1;
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
    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Programming\\GitHub\\HowardHeysSpn\\src\\input03.txt"));
        Scanner in = new Scanner(System.in);
        int numTestCase = in.nextInt();
        for (int numTest = 0; numTest < numTestCase; numTest++) {
            int n = in.nextInt();
            int m = in.nextInt();
            HashMap<Node, Node> vertices = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int t = in.nextInt();
                Node v1 = new Node(t);
                t = in.nextInt();
                Node v2 = new Node(t);
                if (!vertices.containsKey(v1)) {
                    vertices.put(v1, v1);
                }
                else {
                    v1 = vertices.get(v1);
                }
                if (!vertices.containsKey(v2)) {
                    vertices.put(v2, v2);
                }
                else {
                    v2 = vertices.get(v2);
                }
                v1.addNeighbor(v2);
                v2.addNeighbor(v1);
            }
            for (int i = 1; i <= n; i++) {
                Node tmp = new Node(i);
                if (!vertices.containsKey(tmp))
                    vertices.put(tmp, tmp);
            }
            int t = in.nextInt();
            Node initial = vertices.get(new Node(t));
            initial.distance = 0;
            Queue<Node> frontierNodes = new LinkedList<>();
            frontierNodes.add(initial);
            Set<Node> exploredNodes = new HashSet<>();

            while (!frontierNodes.isEmpty()) {
                Node currentNode = frontierNodes.remove();
                exploredNodes.add(currentNode);
                for (Node neighbor : currentNode.adjacents) {
                    if (!exploredNodes.contains(neighbor) && !frontierNodes.contains(neighbor)) {
                        frontierNodes.add(neighbor);
                        neighbor.distance = currentNode.distance + 1;
                    }
                }
            }

            int i = 0;
            for (Node v : vertices.keySet()) {
                if (v.equals(initial)) continue;
                if (v.distance != -1) {
                    System.out.print(v.distance * 6);
                }
                else {
                    System.out.print(v.distance);
                }
                if (i != vertices.size() - 1) {
                    System.out.print(" ");
                }
                i++;
            }
            if (numTest != numTestCase - 1) {
                System.out.println();
            }
        }
    }
}
