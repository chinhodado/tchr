package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {
    public static class Vertex {
        int id;
        int distance = -1;
        HashMap<Vertex, Integer> adjacents;
        public Vertex(int id) {
            this.id = id;
            adjacents = new HashMap<Vertex, Integer>();
        }
        public void addNeighbor(Vertex v, int distance) {
            if (adjacents.containsKey(v)) {
                int prevDistance = adjacents.get(v);
                if (distance < prevDistance && distance != -1) {
                    adjacents.put(v, distance);
                }
            }
            else {
                adjacents.put(v, distance);
            }
        }
        public boolean isNeighbor(Vertex v) {
            return adjacents.containsKey(v);
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
                int distance = in.nextInt();
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
                v1.addNeighbor(v2, distance);
                v2.addNeighbor(v1, distance);
            }
            for (int i = 1; i <= n; i++) {
                Vertex tmp = new Vertex(i);
                if (!vertices.containsKey(tmp))
                    vertices.put(tmp, tmp);
            }
            int t = in.nextInt();
            Vertex initial = vertices.get(new Vertex(t));
            initial.distance = 0;
            PriorityQueue<Vertex> frontierNodes = new PriorityQueue<Vertex>(n, new Comparator<Vertex>() {
                @Override
                public int compare(Vertex node1, Vertex node2) {
                    return node1.distance - node2.distance;
                }
            });
            frontierNodes.add(initial);
            Set<Vertex> exploredNodes = new HashSet<Vertex>();

            while (!frontierNodes.isEmpty()) {
                Vertex currentNode = frontierNodes.remove();
                exploredNodes.add(currentNode);
                for (Vertex neighbor : currentNode.adjacents.keySet()) {
                    if (!exploredNodes.contains(neighbor)) {
                        int newDistance = currentNode.distance + currentNode.adjacents.get(neighbor);
                        if (frontierNodes.contains(neighbor)) {
                            if (newDistance < neighbor.distance) {
                                neighbor.distance = newDistance;

                                // We have to remove and add the node again to force the heap to update.
                                // Not ideal for performance since remove() is O(n), but oh well...
                                frontierNodes.remove(neighbor);
                                frontierNodes.add(neighbor);
                            }
                        }
                        else {
                            neighbor.distance = newDistance;
                            frontierNodes.add(neighbor);
                        }
                    }
                }
            }

            int i = 0;
            for (Vertex v : vertices.keySet()) {
                if (v.equals(initial)) continue;

                System.out.print(v.distance);
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
