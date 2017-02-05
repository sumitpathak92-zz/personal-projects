import java.util.*;
import java.io.*;

public class LinkedListDelete {
    
    ListNode head;
    void deleteNode(int key) {
        ListNode temp = head, prev = null;
        if(temp!=null && temp.data==key) {
            head = temp.next; //changed head
            return;
        }

        while(temp!=null && temp.data!=key) {
            prev = temp;
            temp = temp.next;
        }

        if(temp==null) return;

        prev.next = temp.next;
    }

    public void push(int new_data) {
        ListNode newNode = new ListNode(new_data);
        newNode.next = head;
        head = newNode;
    }

    public void printList() {
        ListNode tnode = head;
        while(tnode!=null) {
            System.out.println(tnode.data+" ");
            tnode = tnode.next;
        }
    }

    public static void main(String[] args){
        LinkedListDelete llist = new LinkedListDelete();
        llist.push(7);
        llist.push(1);
        llist.push(3);
        llist.push(2);

        llist.deleteNode(1);

        System.out.println("Linked List after deletion :");
        llist.printList();
    }

}

class ListNode {

    int data;
    ListNode next;
    ListNode(int data) {
        this.data = data;
        next = null;
    }
}
