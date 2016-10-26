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
    static class Vertex {
        int id;
        int distance = -1;
        Set<Vertex> adjacents;
        public Vertex(int id) {
            this.id = id;
            adjacents = new HashSet<Vertex>();
        }
        public void addNeighbor(Vertex v) {
            if (!adjacents.contains(v)) {
                adjacents.add(v);
            }
        }
        public boolean isNeighbor(Vertex v) {
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
            final Vertex other = (Vertex) obj;
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
            HashMap<Vertex, Vertex> vertices = new HashMap<Vertex, Vertex>();
            for (int i = 0; i < m; i++) {
                int t = in.nextInt();
                Vertex v1 = new Vertex(t);
                t = in.nextInt();
                Vertex v2 = new Vertex(t);
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
                Vertex tmp = new Vertex(i);
                if (!vertices.containsKey(tmp))
                    vertices.put(tmp, tmp);
            }
            int t = in.nextInt();
            Vertex initial = vertices.get(new Vertex(t));
            initial.distance = 0;
            Queue<Vertex> frontierNodes = new LinkedList<Vertex>();
            frontierNodes.add(initial);
            Set<Vertex> exploredNodes = new HashSet<Vertex>();

            while (!frontierNodes.isEmpty()) {
                Vertex currentNode = frontierNodes.remove();
                exploredNodes.add(currentNode);
                for (Vertex neighbor : currentNode.adjacents) {
                    if (!exploredNodes.contains(neighbor) && !frontierNodes.contains(neighbor)) {
                        frontierNodes.add(neighbor);
                        neighbor.distance = currentNode.distance + 1;
                    }
                }
            }

            int i = 0;
            for (Vertex v : vertices.keySet()) {
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
