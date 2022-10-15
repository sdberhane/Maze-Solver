import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Contains the API necessary for a simple, directed graph.
 * By convention, the n vertices will be labeled 0,1,...,n-1. The edge weights can be any int value.
 * Labeling vertices from 0 to n-1
 * Uses O(m + n) space.
 */
public class Graph {
    
    private int size;
    private ArrayList<HashMap<Integer, Integer>> adjacencyList;
    
    
    /**
     * Initializes a graph of size {@code n}. All valid vertices in this graph thus have integer
     * indices in the half-open range {@code [0, n)}, n > 0.
     * <p/>
     *
     * @param n the number of vertices in the graph
     * @throws IllegalArgumentException if {@code n} is zero or negative
     * @implSpec This method runs in expected O(n) time
     */
    public Graph(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        
        this.size = n;
        this.adjacencyList = new ArrayList<HashMap<Integer, Integer>>();
        
        for (int i = 0; i < n; i++) {
            this.adjacencyList.add(i, new HashMap<Integer, Integer>());
        } 
    }

    /**
     * Returns the number of vertices in the graph.
     * <p/>
     *
     * @return the number of vertices in the graph
     * @implSpec This method should run in expected O(1) time.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Determines if there's an directed edge from u to v.
     * <p/>
     *
     * @param u a vertex
     * @param v a vertex
     * @return {@code true} if the {@code u-v} edge is in this graph
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method runs in expected O(1) time.
     */
    public boolean hasEdge(int u, int v) {
        if (u < 0 || u >= this.size) {
            throw new IllegalArgumentException("u is not in graph");
        }
        if (v < 0 || v >= this.size) {
            throw new IllegalArgumentException("v is not in graph");
        }
        
        return this.adjacencyList.get(u).containsKey(v);
    }

    /**
     * Returns the weight of an the directed edge {@code u-v}.
     * <p/>
     *
     * @param u source vertex
     * @param v target vertex
     * @return the edge weight of {@code u-v}
     * @throws NoSuchElementException   if the {@code u-v} edge does not exist
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method runs in expected O(1) time.
     */
    public int getWeight(int u, int v) {
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }
        if (u < 0 || u >= this.size) {
            throw new IllegalArgumentException("u is not in graph");
        }
        if (v < 0 || v >= this.size) {
            throw new IllegalArgumentException("v is not in graph");
        }
        
        return this.adjacencyList.get(u).get(v);
    }

    /**
     * Creates an edge from {@code u} to {@code v} if it does not already exist. 
     *
     * @param u      the source vertex to connect
     * @param v      the target vertex to connect
     * @param weight the edge weight
     * @return {@code true} if the graph changed as a result of this call, false otherwise (i.e., if
     * the edge is already present)
     * @throws IllegalArgumentException if a specified vertex does not exist or if u == v
     * @implSpec This method runs in expected O(1) time
     */
    public boolean addEdge(int u, int v, int weight) {
        if (u == v) {
            throw new IllegalArgumentException();
        }
        if (u < 0 || u >= this.size) {
            throw new IllegalArgumentException("u is not in graph");
        }
        if (v < 0 || v >= this.size) {
            throw new IllegalArgumentException("v is not in graph");
        }
        
        if (!hasEdge(u, v)) {
            this.adjacencyList.get(u).put(v, weight);
            return true;
        }
        return false;
    }

    /**
     * Returns the out-neighbors of the specified vertex.
     *
     * @param v the vertex
     * @return all out neighbors of the specified vertex or an empty set if there are no out
     * neighbors
     * @throws IllegalArgumentException if the specified vertex does not exist
     * @implSpec This method runs in expected O(outdeg(v)) time.
     */
    public Set<Integer> outNeighbors(int v) {
        if (v < 0 || v >= this.size) {
            throw new IllegalArgumentException("v is not in graph");
        }
        
        return this.adjacencyList.get(v).keySet();
    }
    
    ArrayList<HashMap<Integer, Integer>> getGraph() {
        return this.adjacencyList;
    }
}