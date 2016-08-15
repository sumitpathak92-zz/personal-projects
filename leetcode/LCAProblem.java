import java.util.*;
import java.io.*;

public class LCAProblem {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right= new TreeNode(8);
        System.out.println(lcaproblem(root, root.left.right, root.right.right));
    }
    
    public static int lcaproblem(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        parent.put(root, null);
        st.push(root);

        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = st.pop();
            if(node.left!=null) {
                parent.put(node.left, node);
                st.push(node.left);
            }
            if(node.right!=null) {
                parent.put(node.right, node);
                st.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while(p!=null) {
            ancestors.add(p);
            p=parent.get(p);
        }
        while(!ancestors.contains(q)) 
            q = parent.get(q);
        return q.val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x)
    {
        val = x;
        this.left=null;
        this.right=null;
    }
}
