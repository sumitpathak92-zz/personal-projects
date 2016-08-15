import java.util.*;
import java.io.*;


public class MinDepthBinaryTree {

    public static void main(String[] args) {
        //driver function
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("minimum depth is "+ minimumdepthTree(root));
    }

    static int minimumdepthTree(Node root) {
        //corner case 
        if (root==null) 
            return 0;
        //base case : leaf node . here height is one 
        if(root.left==null && root.right==null) 
            return 1;
        // if left subtree is null, recur for right subtree
        if(root.left==null) 
            return minimumdepthTree(root.right) + 1;
        //if right subtree is null, recur for left subtree
        if(root.right==null) { 
            return minimumdepthTree(root.left) +1;
        }

        return Math.min(minimumdepthTree(root.left), minimumdepthTree(root.right)) + 1;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int x) {
        val = x;
        this.left = null;
        this.right = null;
    }
}
