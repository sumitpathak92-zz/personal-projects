import java.util.*;
import java.io.*;

public class BFSImplementation {
    public TreeNode search(TreeNode root, int val) {
        if(root==null || root.key==key) return root;
        if(root.data > val) return search(root.left, val);
        return search(root.right, val);
    }
}

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
