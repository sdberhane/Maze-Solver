import java.util.ArrayList;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static ArrayList<Integer> getShortestPath(Graph g, int src, int tgt) {
        if (tgt == src) {
            ArrayList<Integer> noPath = new ArrayList<Integer>();
            noPath.add(src);
            return noPath;
        }
        
        int[] distance = new int[g.getSize()];
        int[] parent = new int[g.getSize()];
        BinaryMinHeapImpl<Integer, Integer> minHeap = new BinaryMinHeapImpl<Integer, Integer>();

        for (int i = 0; i < g.getSize(); i++) {
            distance[i] = Integer.MAX_VALUE;          
            parent[i] = -1;
        }
        distance[src] = 0;
        
        for (int i = 0; i < g.getSize(); i++) {
            minHeap.add(distance[i], i);
        }
        
        while (!minHeap.isEmpty()) {
            int v = minHeap.extractMin().value;
            
            if (distance[v] == Integer.MAX_VALUE) {
                break;
            }
            
            for (int u : g.outNeighbors(v)) {
                if (distance[v] + g.getWeight(v, u) < distance[u]) {
                    distance[u] = distance[v] + g.getWeight(v, u);
                    
                    if (minHeap.containsValue(u)) {
                        minHeap.decreaseKey(u, distance[u]);
                    }
                    
                    parent[u] = v;
                } else if (distance[v] + g.getWeight(v, u) == distance[u]) {
                    int vTotal = 1, uTotal = 0;
                    
                    int i = u;
                    while (i != src) {
                        i = parent[i];
                        uTotal++; 
                    }
                    
                    i = v;
                    while (i != src) {
                        i = parent[i];
                        vTotal++;
                    }
                    
                    if (vTotal < uTotal) {
                        parent[u] = v;
                    }
                }
            }
        }
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        int temp = tgt;
        
        while (temp != src) {
            path.add(0, temp);
            temp = parent[temp];
            if (temp == -1) {
                return new ArrayList<Integer>();
            }
        }
        
        path.add(0, src);
        return path;
    }
}
