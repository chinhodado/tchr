package hackerRank.contests.weekOfCode.w22;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Chin on 13-Aug-16.
 * TODO: still not fast enough in some cases
 */
public class SequentialPrefixFunction {
    static class Node {
        int id;
        int maxBorder;
        Node parent;
        Set<Node> adjacents = new HashSet<>();
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
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> s = new ArrayList<>(200000);
        ArrayList<Integer> border = new ArrayList<>(200000);
        StringBuilder sb = new StringBuilder();
        int last_idx = 0; // last idx that we have calculated the border for
        Node currentNode = new Node(-1);
        currentNode.maxBorder = -1;
        Node root = currentNode; // dummy node

        for (int nx = 0; nx < n; nx++) {
            String type = in.next();
            if (type.equals("+")) {
                int x = in.nextInt();
                s.add(x);

                int max_border_length = 0;
                if (s.size() == 1) {
                    border.add(0, -1);
                    last_idx = 0;
                    max_border_length = 0;

                    Node newNode = new Node(x);
                    if (currentNode.adjacents.contains(newNode)) {
                        for (Node tmpNode : currentNode.adjacents) {
                            if (tmpNode.id == x) { // bad because java sucks
                                currentNode = tmpNode;
                                break;
                            }
                        }
                    }
                    else {
                        newNode.maxBorder = -1;
                        currentNode.adjacents.add(newNode);
                        newNode.parent = currentNode;
                        currentNode = newNode;
                    }
                }
                else {
                    Node newNode = new Node(x);
                    if (currentNode.adjacents.contains(newNode)) {
                        for (Node tmpNode : currentNode.adjacents) {
                            if (tmpNode.id == x) { // bad because java sucks
                                currentNode = tmpNode;
                                break;
                            }
                        }
                        max_border_length = currentNode.maxBorder + 1;
                        border.add(currentNode.maxBorder);
                        last_idx++;
                    }
                    else {
                        int i = last_idx + 1, j = border.get(border.size() - 1);

                        while(j >= 0 && !s.get(i).equals(s.get(j+1))) j = border.get(j);
                        if (s.get(i).equals(s.get(j+1))) j++;
                        border.add(i++, j);

                        last_idx = i - 1; // = n-1
                        max_border_length = j + 1;
                        newNode.maxBorder = j;
                        currentNode.adjacents.add(newNode);
                        newNode.parent = currentNode;
                        currentNode = newNode;
                    }
                }
                sb.append(max_border_length).append("\n");
            }
            else {
                if (s.size() > 0) {
                    s.remove(s.size() - 1);
                    border.remove(border.size() - 1);
                    last_idx--;
                }

                if (currentNode.parent != null) {
                    currentNode = currentNode.parent;
                }
                int max_border_length = s.size() == 0? 0 : (currentNode.maxBorder + 1);
                sb.append(max_border_length).append("\n");
            }
        }

        System.out.println(sb);
    }
}