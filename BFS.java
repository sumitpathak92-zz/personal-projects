import java.util.*;
import java.io.*;

public class BFS {

	private int V; //no of vertices 
	private LinkedList<Integer> adj[]; //for adjacency list representation

	//constructor
	BFS(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; i++)
		{
			adj[i] = new LinkedList();
		}
	}

	// function to add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// breadth first search traversal starts here from a given source s
	void BreadthFirstSearch(int s) {
		 //mark all the nodes as not visited by default
		boolean visited[] = new boolean[V];
		 //create a queue for BFS traversal
		LinkedList<Integer> queue = new LinkedList<Integer>();

		//mark source as visited	
		visited[s] = true;
		queue.add(s);

		while(queue.size()!=0) {
			s = queue.poll();
			System.out.println(s + " ");
			Iterator<Integer> i = adj[s].iterator();

			while(i.hasNext()) {
				int n = i.next();
				if(!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String[] args) {

		BFS g = new BFS(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Breadth First Traversal starting from vertex 2");
 
        g.BreadthFirstSearch(2);
	}
}