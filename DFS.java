import java.util.*;
import java.io.*;


public class DFS {

	private static int V;
	private LinkedList<Integer> adjMatrix;

	//constructor
	DFS(int v) {
		v = V;
		adjMatrix = new LinkedList[v];
		for (int i = 0; i<v; i++) {
			adjMatrix[i] = new LinkedList<Integer>();
		}
	}

	//add edge to the vertex
	public void addEdge(int v, int w) {
		adjMatrix[v].add(w);
	}

	//start DFS from source s
//	void DepthFirstSearch(int s) {

//		boolean visited[] = new boolean[V];
		//mark the source as visited

//		visited[s] = true;
//		Stack st = new Stack();
//		st.push(s);

//		while(!st.isEmpty()) {
//			s = Integer.parseInt(st.pop());
//			System.out.println(s + " ");
//			Iterator<Integer> i = adjMatrix.iterator();
///			while(i.hasNext()) {
//				int n = i.next();
//				if(!visited[n]) {
//					visited[n] = true;
//					st.push(n);
//				}
//			}
//		}
//	}
    
    void DFSToCheckCycle(int s) {
        boolean visited[] = new boolean[V];
        boolean backedgeflag = false;

        visited[s]=true;
        Stack st = new Stack();
        st.push(s);


        while(!st.isempty() && backedgeflag==false) {
            s = Integer.parseInt(st.pop());
            Iterator<Integer> i = adjMatrix.iterator();
            while(i.hasNext()) {
               int n = i.next();
               if(!visited[n]) {
                    visited[n]=true;
                    st.push(n);
               }
               else if(visited[n]) {
                    backedgeflag=true;
                    break;
               }
            }

        }
        System.out.println("there is a cysle in the graph");
    }
	public static void main(String[] args) {

		DFS d = new DFS(4);

		d.addEdge(0, 1);
        d.addEdge(0, 2);
        d.addEdge(1, 2);
        d.addEdge(2, 0);
        d.addEdge(2, 3);
        d.addEdge(3, 3);

        System.out.println("Depth First Traversal Leads to this pattern");
        d.DFSToCheckCycle(2);
	}
}
