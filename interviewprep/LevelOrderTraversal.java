import java.util.*;
import java.io.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        
        TreeNode first = new TreeNode(7);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(4);
        TreeNode fourth = new TreeNode(8);
        TreeNode fifth = new TreeNode(9);
        TreeNode sixth = new TreeNode(3);
        first.l = second;
        first.r = third;
        first.l.l = fourth;
        first.l.r = fifth;
        first.r.r = sixth;
        levelOrderTraversal(first);
    }

    public static void levelOrderTraversal(TreeNode root) {
        if(root==null)
            System.out.println("null");
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty())
        {
            TreeNode tmp = q.remove();
            System.out.println(tmp.data); //print root data
            if(tmp.l!=null) q.offer(tmp.l);
            if(tmp.r!=null) q.offer(tmp.r);
            else{
                continue;
            }
        }
    }

}


class TreeNode {
    public int data;
    public TreeNode l;
    public TreeNode r;

    //constructor
    public TreeNode(int data, TreeNode left, TreeNode right){
        this.data=data;
        this.l = left;
        this.r = right;
    }
    //constructor overloading
    public TreeNode(int data) {
        this.data = data;
    }
}
