import java.util.*;
import java.io.*;

public class BFS {
    
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addToAdj(0, 1);
        g.addToAdj(0, 2);
        g.addToAdj(1, 2);
        g.addToAdj(2, 0);
        g.addToAdj(2, 3);
        g.addToAdj(3, 3);
        //System.out.println(g.data)
        System.out.println("graph traversal BFS");
        System.out.println(g.visited[2]);
        BFSTraversal(2, g);

    }

    public static void BFSTraversal(int src, Graph g) {
        Queue<Integer> q = new LinkedList<Integer>();
        g.visited[src] = true;
        q.add(src);
        while(!q.isEmpty()) {
            src = q.poll();
            System.out.println(src + " ");
            Iterator<Integer> i = g.adj[src].iterator();
            while(i.hasNext()) {
                int n = i.next();
                if (!g.visited[n]) {
                    g.visited[n] = true;
                    q.add(n);
                }
            }
        }
    }
    
}


class Graph {
    public int V; //no of vertices in graph
    public boolean visited[];
    public LinkedList<Integer> adj[];
    //constructor
    public Graph(int v) {
        V = v;
        for(int i=0;i<v; i++) {
            adj[i] = new LinkedList();      
            visited[i] = false;
        }
    }
    
    //add to adjacency list
    public void addToAdj(int v, int w) {
        adj[v].add(w);
    }

}

