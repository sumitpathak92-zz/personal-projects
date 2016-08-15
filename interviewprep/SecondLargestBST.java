import java.io.*;
import java.util.*;

public class SecondLargestBST {
    
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        node1.left = new TreeNode(8);
        node1.right = new TreeNode(12);
        TreeNode node2 = new TreeNode(13);
        TreeNode node3 = new TreeNode(11);
        node1.right.right = node2;
        node1.right.left = node3;
        node2.right = new TreeNode(15);
        System.out.println("second largest is "+returnSecondLargest(node1).val);
    }

    public static TreeNode returnSecondLargest(TreeNode root) {
        if(root.right.right==null) return root;
        return returnSecondLargest(root.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) {
         val=x;
    }
}
