package hackerRank.contests.weekOfCode.w28;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Chin on 12-Jan-17.
 */
public class TheValueOfFriendship {
    private static class Node {
        int id;
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

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        fillBinomialCoeff(100009, 3);

        for (int qx = 0; qx < q; qx++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Map<Node, Node> vertices = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();

                Node v1 = new Node(x);
                Node v2 = new Node(y);
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

            List<Set<Node>> components = new ArrayList<>();

            Set<Node> toExplore = new HashSet<>(vertices.keySet());
            while (!toExplore.isEmpty()) {
                Node initial = null;

                // get one elem from set
                for (Node tmp : toExplore) {
                    initial = tmp;
                    break;
                }

                Queue<Node> frontierNodes = new LinkedList<>();
                frontierNodes.add(initial);
                Set<Node> exploredNodes = new HashSet<>();

                while (!frontierNodes.isEmpty()) {
                    Node currentNode = frontierNodes.remove();
                    exploredNodes.add(currentNode);
                    toExplore.remove(currentNode);
                    for (Node neighbor : currentNode.adjacents) {
                        if (!exploredNodes.contains(neighbor) && !frontierNodes.contains(neighbor)) {
                            frontierNodes.add(neighbor);
                        }
                    }
                }

                components.add(exploredNodes);
            }

            List<Component> cps = new ArrayList<>();
            BigInteger totalFt = BigInteger.ZERO;
            int total_mt = 0;
            for (Set<Node> component : components) {
                int nt = component.size();
                int totalDegree = 0;
                for (Node tmp : component) {
                    totalDegree += tmp.adjacents.size();
                }
                int mt = totalDegree / 2;
                long bin = binomialCoeff(nt+1, 3);
                long ft = 2 * bin;
                totalFt = totalFt.add(BigInteger.valueOf(ft));
                total_mt += nt - 1;
                cps.add(new Component(ft, component, mt));
            }

            cps.sort(Comparator.comparingLong(o -> o.ft));

            BigInteger total_ft_raw = BigInteger.ZERO;
            for (Component c : cps) {
                total_ft_raw = total_ft_raw.add(c.n_n1);
            }

            for (int i = cps.size() - 1; i >= 0; i--) {
                BigInteger tmp = BigInteger.ZERO;
                Component comp_i = cps.get(i);
                for (int j = cps.size() - 1; j > i; j--) {
                    tmp = tmp.add(BigInteger.valueOf(comp_i.nodes.size() - 1).multiply(cps.get(j).n_n1));
                }
                totalFt = totalFt.add(tmp);
            }

            totalFt = totalFt.add(total_ft_raw.multiply(BigInteger.valueOf((m-total_mt))));

            System.out.println(totalFt);
        }
    }

    // Returns value of Binomial Coefficient C(n, k)
    static long binomialCoeff(int n, int k) {
        return C[n][k];
    }

    static long C[][];
    static void  fillBinomialCoeff(int n, int k) {
        C = new long[n + 1][k + 1];
        int i, j;

        // Calculate  value of Binomial Coefficient in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;

                    // Calculate value using previously stored values
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
    }

    static class Component {
        long ft;
        Set<Node> nodes;
        int mt;
        BigInteger n_n1;
        public Component (long ft, Set<Node> nodes, int mt) {
            this.ft = ft;
            this.nodes = nodes;
            this.mt = mt;
            this.n_n1 = BigInteger.valueOf((long)nodes.size() * (nodes.size() - 1));
        }
    }
}
