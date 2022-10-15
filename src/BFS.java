import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BFS {

    public static int[] bfsTraversal(Graph g, int root) {
        
        ArrayList<HashMap<Integer, Integer>> adjacencyList = g.getGraph();
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<Integer>();
        Boolean[] discovered = new Boolean[g.getSize()];
        int[] parent = new int[g.getSize()];
          
        for (int i = 0; i < g.getSize(); i++) {
            if (i == root) {
                discovered[i] = true;
            } else {
                discovered[i] = false;
            }
            parent[i] = -1;
        }
        
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int v = deque.pollFirst();
            for (int u : adjacencyList.get(v).keySet()) {
                if (!discovered[u]) {
                    discovered[u] = true;
                    deque.addLast(u);
                    parent[u] = v;
                }
            }
        }
     
        return parent;
    }
    
    static ArrayList<Integer> findPath(Graph g, int src, int tgt) {
        int[] parent = new int[g.getSize()];
        Boolean[] discovered = new Boolean[g.getSize()];
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<Integer>();
        ArrayList<Integer> shortestPath = new ArrayList<Integer>();
        
        for (int i = 0; i < g.getSize(); i++) {
            parent[i] = i;
            discovered[i] = false;
        }
        
        discovered[src] = true;
        deque.addFirst(src);
        
        while (!deque.isEmpty()) {
            int v = deque.pollFirst();
            
            for (int u : g.outNeighbors(v)) {
                if (!discovered[u]) {
                    deque.addLast(u);
                    parent[u] = v;
                    discovered[u] = true;
                }
            }
        }
        
        int temp = tgt;
        shortestPath.add(temp);
        while (temp != src) {
            shortestPath.add(parent[temp]);
            temp = parent[temp];
            if (parent[temp] == temp) {
                break;
            }
        }
        
        Collections.reverse(shortestPath);
        if (shortestPath.contains(src)) {
            return shortestPath;
        } else {
            return new ArrayList<Integer>();
        }
    }
}
