import java.io.*;
import java.util.*;

public class Checkbalancetreealgo {
    
      //check for the minimum and maximum depth of the tree
      //if their difference is greater than 1 than its unnbalanced otherwise it is
      
     public int maxDepth(TreeNode node) {
        if (node == null) 
            return 0;
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
     }

     public int minDepth(TreeNode node) {
        if(node==null)
            return 0;
        return 1 + Math.min(minDepth(node.left), minDepth(node.right));
     }

     public boolean checkBalanced(TreeNode root) {
        return (maxDepth(root)-minDepth(root)) <= 1;
     }

    }

class TreeNode {
        
    int data;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data, TreeNode l, TreeNode r) {
        this.data = data;
        this.left = l;
        this.right = r;
     }

    }
}
