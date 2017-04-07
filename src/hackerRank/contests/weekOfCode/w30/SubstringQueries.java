package hackerRank.contests.weekOfCode.w30;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 19-Mar-17.
 */
public class SubstringQueries {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next();
        }

        for (int qx = 0; qx < q; qx++) {
            int x = in.nextInt();
            int y = in.nextInt();
            String s = arr[x] + '\1' + arr[y] + '\2';
            SuffixTree.Node tree = SuffixTree.buildSuffixTree(s);
            SuffixTree.lcsLength = 0;
            SuffixTree.lcsBeginIndex = 0;
            // find longest common substring
            SuffixTree.lcs(tree, arr[x].length(), arr[x].length() + arr[y].length() + 1);
            System.out.println(SuffixTree.lcsLength);
        }
    }

    public static class SuffixTree {
        static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789\1\2";

        public static class Node {
            int begin;
            int end;
            int depth; // distance in characters from root to this node
            Node parent;
            Node[] children;
            Node suffixLink;

            Node(int begin, int end, int depth, Node parent) {
                this.begin = begin;
                this.end = end;
                this.parent = parent;
                this.depth = depth;
                children = new Node[ALPHABET.length()];
            }
        }

        public static Node buildSuffixTree(CharSequence s) {
            int n = s.length();
            byte[] a = new byte[n];
            for (int i = 0; i < n; i++) a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
            Node root = new Node(0, 0, 0, null);
            Node node = root;
            for (int i = 0, tail = 0; i < n; i++, tail++) {
                Node last = null;
                while (tail >= 0) {
                    Node ch = node.children[a[i - tail]];
                    while (ch != null && tail >= ch.end - ch.begin) {
                        tail -= ch.end - ch.begin;
                        node = ch;
                        ch = ch.children[a[i - tail]];
                    }
                    if (ch == null) {
                        node.children[a[i]] = new Node(i, n, node.depth + node.end - node.begin, node);
                        if (last != null) last.suffixLink = node;
                        last = null;
                    } else {
                        byte t = a[ch.begin + tail];
                        if (t == a[i]) {
                            if (last != null) last.suffixLink = node;
                            break;
                        } else {
                            Node splitNode = new Node(ch.begin, ch.begin + tail, node.depth + node.end - node.begin, node);
                            splitNode.children[a[i]] = new Node(i, n, ch.depth + tail, splitNode);
                            splitNode.children[t] = ch;
                            ch.begin += tail;
                            ch.depth += tail;
                            ch.parent = splitNode;
                            node.children[a[i - tail]] = splitNode;
                            if (last != null) last.suffixLink = splitNode;
                            last = splitNode;
                        }
                    }
                    if (node == root) {
                        --tail;
                    } else {
                        node = node.suffixLink;
                    }
                }
            }
            return root;
        }

        static int lcsLength;
        static int lcsBeginIndex;

        // traverse suffix tree to find longest common substring
        public static int lcs(Node node, int i1, int i2) {
            if (node.begin <= i1 && i1 < node.end) {
                return 1;
            }
            if (node.begin <= i2 && i2 < node.end) {
                return 2;
            }
            int mask = 0;
            for (char f = 0; f < ALPHABET.length(); f++) {
                if (node.children[f] != null) {
                    mask |= lcs(node.children[f], i1, i2);
                }
            }
            if (mask == 3) {
                int curLength = node.depth + node.end - node.begin;
                if (lcsLength < curLength) {
                    lcsLength = curLength;
                    lcsBeginIndex = node.begin;
                }
            }
            return mask;
        }
    }
}
