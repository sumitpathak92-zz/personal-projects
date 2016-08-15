import java.util.NoSuchElementException;
import java.util.*;

public class Queuewithstacks {
    private Stack<Item> s1;
    private Stack<Item> s2;

    //create an empty queue
    public Queuewithstacks() {
        s1=new Stack<Item>();
        s2=new Stack<Item>();
    }

    private void moveItemfroms1tos2() {
        while(!s1.isEmpty())
            s2.push(s1.pop());
    }

    //check if the queue is empty
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    //return no of items in queue
    public int size() {
        return s1.size() + s2.size();
    }

    //return item least recently added to queue
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Queue Underflow");
        if(s2.isEmpty()) moveItemfroms1tos2();
        return s2.peek();
    }

    //enqueue items in queue
    public void enqueue(Item item) {
       s1.push(item); 
    }

    public Item dequeue() {
        if(isEmpty()) 
            throw NoSuchElementException("Queue Overflow");
        if (s2.isEmpty()) 
            moveItemfroms1tos2();
        return s2.pop();
    }

    public static void main(String[] args) {
        Queuewithstacks<String> q = new Queuewithstacks<String>();
        while(!StdIn.isEmpty) {
            String item = StdIn.readString();
            if(item.equals("-")) 
                q.enqueue(item);
            else if(!q.isEmpty()) 
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
