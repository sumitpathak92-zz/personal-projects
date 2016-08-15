public class GraphImpl
{
}

public class GraphAdjList extends Graph 
{
    private Map<Integer, ArrayList<Integer>> adjListMap;
}

public abstract class Graph
{
    private int numVertices;
    private int numEdges;

    public Graph()
    {
        numVertices = 0;
        numEdges = 0;
    }

    public int getNoOfVertices()
    {
        return numVertices;
    }
    public int getNumEdges()
    {
        return numEdges;
    }

    public void addVertex()
    {
        implementAddVertex();
        numVertices++;
    }
    public abstract void implementAddVertex()
    {
       int v = getNoOfVertices();
       ArrayList<Integer> neighbors = new ArrayList<Integer>();
       adjListMap = new Map<Integer, ArrayList<Integer>>();
       adjListMap.put(v, neighbors);
        //logic for adding the vertices
    }

    public void addEdge(int v, int w)
    {
        (adjListMap.get(v)).add(w);
        //adds new vertex to the neighbors list 
    }
    
    public abstract List<Integer> getNeighbour(int v)
    {
        return adjListMap.get(v);
        //returns the list of neighbours
    }

}
