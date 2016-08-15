import java.util.*;
import java.io.*;

public class RemoveDuplicatesList2 {
    static final maxDupes = 2;
    public void deleteWithAtMostTwo(LinkedListNode head) {
        LinkedListNode current = head;
        while(current.next!=null) {
            if(current.data==current.next.data) {
                if(count<maxDupes) {
                    count +=1;
                    current = current.next;
                }
                if(count=maxDupes) {
                    while(current.data==current.next.data) {
                        current.next=current.next.next;
                    }
                }
            }

            else {
                current=current.next;
            }
        }
}

    public static void main(String[] args) {
        
    }
}

public class LinkedListNode {
    int data;
    LinkedListNode next;
    LinkedListNode(int x) {
        data=x;
    }
}
