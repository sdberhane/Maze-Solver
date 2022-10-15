import java.util.ArrayList;

import java.util.List;

final public class MazeSolver {
    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        Graph mazeRep = new Graph(maze.length * maze[0].length);

        int srcNum = (src.getY() * maze[0].length) + src.getX();
        int tgtNum = (tgt.getY() * maze[0].length) + tgt.getX();
        int xLim = maze[0].length - 1;
        int yLim = maze.length - 1;


        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                List<Coordinate> adjacentCoords = nextTo(maze, i, j);
                if (adjacentCoords != null) {
                    for (Coordinate k : adjacentCoords) {
                        int v = (k.getY() * maze[0].length) + k.getX();
                        int u = (j * maze[0].length) + i;
                        if (u != v) {
                            mazeRep.addEdge(u, v, 1);
                        }
                    }
                }
            }
        }

        // ArrayList<Integer> path = BFS.findPath(mazeRep, srcNum, tgtNum);
        ArrayList<Integer> path = Dijkstra.getShortestPath(mazeRep, srcNum, tgtNum);
        
        ArrayList<Coordinate> shortestPath = new ArrayList<Coordinate>();
        
        for (int i = 0; i < path.size(); i++) {
            int node = path.get(i);
            
            int x = (int) (node / (xLim + 1));
            int y = node % (xLim + 1);
            
            shortestPath.add(new Coordinate(y, x));
        }
        return shortestPath;


    }

    static List<Coordinate> nextTo(int[][] maze, int i, int j) {
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x >= 0 && x <= maze[0].length - 1 &&
                        y >= 0 && y <= maze.length - 1) {
                    if (maze[y][x] == 0 && Math.abs(y - j) + Math.abs(x - i) == 1) {
                        coords.add(new Coordinate(x, y));
                    }
                }
            }
        }
        return coords;
    }
}