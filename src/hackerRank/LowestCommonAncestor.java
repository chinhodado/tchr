package hackerRank;

import dataStructure.SegmentTree;

import java.util.*;

/**
 * Created by Chin on 09-Jan-17.
 */
public class LowestCommonAncestor {
    // Java program to find LCA of u and v by reducing problem to RMQ

    // A binary tree node
    static class Node {
        Node left, right;
        int data;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root;
    int v = 9; // v is the highest value of node in our tree
    int euler[] = new int[2 * v - 1]; // for euler tour sequence
    int level[] = new int[2 * v - 1]; // level of nodes in tour sequence
    int f_occur[] = new int[2 * v - 1]; // to store 1st occurance of nodes
    int fill; // variable to fill euler and level arrays

    // Recursive version of the Euler tour of T
    void eulerTour(Node node, int l) {
        /* if the passed node exists */
        if (node != null) {
            euler[fill] = node.data; // insert in euler array
            level[fill] = l;         // insert l in level array
            fill++;                  // increment index

            /* if unvisited, mark first occurrence */
            if (f_occur[node.data] == -1)
                f_occur[node.data] = fill - 1;

            /* tour left subtree if exists, and remark euler
               and level arrays for parent on return */
            if (node.left != null) {
                eulerTour(node.left, l + 1);
                euler[fill] = node.data;
                level[fill] = l;
                fill++;
            }

            /* tour right subtree if exists, and remark euler
               and level arrays for parent on return */
            if (node.right != null) {
                eulerTour(node.right, l + 1);
                euler[fill] = node.data;
                level[fill] = l;
                fill++;
            }
        }
    }

    // returns LCA of node n1 and n2 assuming they are present in tree
    int findLCA(Node node, int u, int v) {
        /* Mark all nodes unvisited.  Note that the size of
           firstOccurrence is 1 as node values which vary from
           1 to 9 are used as indexes */
        Arrays.fill(f_occur, -1);

        /* To start filling euler and level arrays from index 0 */
        fill = 0;

        /* Start Euler tour with root node on level 0 */
        eulerTour(root, 0);

        /* construct segment tree on level array */
        SegmentTree sgTree = new SegmentTree(level);

        /* If v before u in Euler tour.  For RMQ to work, first
         parameter 'u' must be smaller than second 'v' */
        if (f_occur[u] > f_occur[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        // Starting and ending indexes of query range
        int qs = f_occur[u];
        int qe = f_occur[v];

        // query for index of LCA in tour
        int index = sgTree.getMin(qs, qe)[1];

        /* return LCA node */
        return euler[index];
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        LowestCommonAncestor tree = new LowestCommonAncestor();

        // Let us create the Binary Tree as shown in the diagram.
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.left.right.left = new Node(8);
        tree.root.left.right.right = new Node(9);

        int u = 3, v = 1;
        System.out.println("The LCA of node " + u + " and " + v + " is node "
                + tree.findLCA(tree.root, u, v));
    }
}
