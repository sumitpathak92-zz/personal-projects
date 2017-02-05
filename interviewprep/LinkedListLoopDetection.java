import java.util.*;
import java.io.*;

public class LinkedListLoopDetection {
   
    public static int detectAndRemoveLoop(ListNode node){
    
        ListNode slow = node, fast = node;
        while(slow!=null && fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                removeLoop(slow, node);
                return 1;
            }
        }
        return 0;
    }

    public static void removeLoop(ListNode loop, ListNode curr) {
        ListNode ptr1, ptr2 = null;
        ptr1 = curr;
        while(1==1){
            ptr2 = loop;
            while(ptr2.next!=loop && ptr2.next!=ptr1) {
                ptr2 = ptr2.next;
            }
            if(ptr2.next == ptr1) {
                break;
                }
            ptr1 = ptr1.next;
            ptr2.next = null;
            }
        }
    
    public static void printList(ListNode node) {
        while(node!=null) {
            System.out.println(node.data+ " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(5);
        first.next = new ListNode(6);
        first.next.next = new ListNode(7);
        first.next.next.next = new ListNode(8);
        first.next.next.next.next = first.next;
        //ListNode second = new ListNode(6, third);
        //ListNode third = new ListNode(7, fourth);
        //ListNode fourth = new ListNode(8, fifth);
        //ListNode fifth = new ListNode(9, second);
        
        if(detectAndRemoveLoop(first) == 1) {
            printList(first);
        }
        else {
            System.out.println("No loop detected, so nothing to remove");
        }
    }
}

class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data=data;
            this.next=null;
        }
    }

