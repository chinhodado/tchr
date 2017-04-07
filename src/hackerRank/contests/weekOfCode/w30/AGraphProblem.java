package hackerRank.contests.weekOfCode.w30;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Chin on 18-Mar-17.
 */
public class AGraphProblem {
    private static class Node {
        int id;
        boolean isInTriangle;
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
        public void removeNeighbor(Node v) {
            adjacents.remove(v);
        }
        public int getNumNeighbor() {
            return adjacents.size();
        }
        public Set<Node> getNeighbors() {
            return adjacents;
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

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        // create all nodes
        Map<Integer, Node> vertices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            vertices.put(i, node);
        }

        // add neighbors
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    vertices.get(i).addNeighbor(vertices.get(j));
                }
            }
        }

        // remove nodes with < 2 neighbors
        vertices.entrySet().removeIf(e-> e.getValue().getNumNeighbor() < 2);

        // remove those from all the nodes neighbors
        for (Node node : vertices.values()) {
            node.getNeighbors().removeIf(e -> !vertices.containsKey(e.id));
        }

        // mark all those in triangles
        List<Node> list = new ArrayList<>(vertices.values());
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    Node nodeI = list.get(i);
                    Node nodeJ = list.get(j);
                    Node nodeK = list.get(k);
                    if (nodeI.isNeighbor(nodeJ) && nodeJ.isNeighbor(nodeK) && nodeK.isNeighbor(nodeI)) {
                        nodeI.isInTriangle = true;
                        nodeJ.isInTriangle = true;
                        nodeK.isInTriangle = true;
                    }
                }
            }
        }

        vertices.entrySet().removeIf(e -> !e.getValue().isInTriangle);
        for (Node node : vertices.values()) {
            node.getNeighbors().removeIf(e -> !e.isInTriangle);
        }

        List<Set<Node>> components = getConnectedComponents(vertices.values());
        Set<Node> bestComponent = null;
        double bestRatio = -1;
        for (Set<Node> component : components) {
            int numTriangle = getNumTriangle(component);
            double ratio = numTriangle / 1.0 / component.size();
            if (ratio > bestRatio) {
                bestRatio = ratio;
                bestComponent = component;
            }
        }

        if (bestComponent != null) {
            System.out.println(bestComponent.size());
            for (Node node : bestComponent) {
                System.out.print((node.id + 1) + " ");
            }
        }
        else {
            System.out.println(1);
            System.out.println(1);
        }
    }

    public static int getNumTriangle(Collection<Node> nodes) {
        List<Node> list = new ArrayList<>(nodes);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    Node nodeI = list.get(i);
                    Node nodeJ = list.get(j);
                    Node nodeK = list.get(k);
                    if (nodeI.isNeighbor(nodeJ) && nodeJ.isNeighbor(nodeK) && nodeK.isNeighbor(nodeI)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static List<Set<Node>> getConnectedComponents(Collection<Node> nodes) {
        List<Set<Node>> components = new ArrayList<>();
        if (nodes.isEmpty()) {
            return components;
        }

        // make a copy
        nodes = new HashSet<>(nodes);

        while (!nodes.isEmpty()) {
            Node initial = nodes.iterator().next();
            Queue<Node> frontierNodes = new LinkedList<>();
            frontierNodes.add(initial);
            Set<Node> exploredNodes = new HashSet<>();

            while (!frontierNodes.isEmpty()) {
                Node currentNode = frontierNodes.remove();
                exploredNodes.add(currentNode);
                for (Node neighbor : currentNode.adjacents) {
                    if (!exploredNodes.contains(neighbor) && !frontierNodes.contains(neighbor)) {
                        frontierNodes.add(neighbor);
                    }
                }
            }
            components.add(exploredNodes);
            nodes.removeAll(exploredNodes);
        }

        return components;
    }
}
