import java.io.*;
import java.util.*;

public class ReverseLLRecursive {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = null;

        //before reversing
        printNodes(head);
        ListNode n = reverseLLRec(head);
        System.out.println("****" +n.data);
        //after reversing
        printNodes(n);
    }

    public static ListNode reverseLLRec(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr!=null) {
            ListNode next1 = curr.next;
            curr.next = prev;
            //curr.next.next= curr;
            prev = curr;
            curr = next1;
        }
        return prev;
    }

    public static void printNodes(ListNode node) {
        while(node!=null) {
            System.out.println(node.data);
            node=node.next;
        }
    }

}

class ListNode{
    int data;
    ListNode next;

    //constructor
    public ListNode(int data){
        this.data = data;
        this.next = null;
    }
}
