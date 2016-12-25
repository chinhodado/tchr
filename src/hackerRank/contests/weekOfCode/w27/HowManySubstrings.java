package hackerRank.contests.weekOfCode.w27;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Too slow
 * Created by Chin on 25-Dec-16.
 */
public class HowManySubstrings {
    // A C++ program to find the count of distinct substring
    // of a string using trie data structure
    private static final int MAX_CHAR = 26;

    // A Suffix Trie (A Trie of all suffixes) Node
    private static class SuffixTrieNode {
        SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];

        // A recursive function to insert a suffix of the s in
        // subtree rooted with this node
        void insertSuffix(String s) {
            // If string has more characters
            if (s.length() > 0) {
                // Find the first character and convert it
                // into 0-25 range.
                int cIndex = s.charAt(0) - 'a';

                // If there is no edge for this character,
                // add a new edge
                if (children[cIndex] == null)
                    children[cIndex] = new SuffixTrieNode();

                // Recur for next suffix
                children[cIndex].insertSuffix(s.substring(1));
            }
        }
    }

    // A Trie of all suffixes
    static class SuffixTrie {
        SuffixTrieNode root;

        // Constructor (Builds a trie of suffixes of the given text)
        SuffixTrie(String s) {
            root = new SuffixTrieNode();

            // Consider all suffixes of given string and insert
            // them into the Suffix Trie using recursive function
            // insertSuffix() in SuffixTrieNode class
            for (int i = 0; i < s.length(); i++)
                root.insertSuffix(s.substring(i));
        }

        //  method to count total nodes in suffix trie
        int countNodesInTrie() {
            return _countNodesInTrie(root);
        }

        // A recursive function to count nodes in trie
        int _countNodesInTrie(SuffixTrieNode node) {
            // If all characters of pattern have been processed,
            if (node == null)
                return 0;

            int count = 0;
            for (int i = 0; i < MAX_CHAR; i++) {
                // if children is not NULL then find count
                // of all nodes in this subtrie
                if (node.children[i] != null)
                    count += _countNodesInTrie(node.children[i]);
            }

            // return count of nodes of subtrie and plus
            // 1 because of node's own count
            return (1 + count);
        }
    }

    // Returns count of distinct substrings of str
    static int countDistinctSubstring(String str) {
        // Construct a Trie of all suffixes
        SuffixTrie sTrie = new SuffixTrie(str);

        // Return count of nodes in Trie of Suffixes
        return sTrie.countNodesInTrie();
    }

    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();
        for (int i = 0; i < q; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            String sub = s.substring(left, right+1);
            System.out.println(countDistinctSubstring(sub) - 1);
        }
    }
}
