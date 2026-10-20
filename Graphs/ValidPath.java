import java.util.*;

public class ValidPath {

    public static void main(String[] args) {

        int n = 3;

        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 0}
        };

        int source = 0;
        int destination = 2;

        System.out.println(
                validPath(n, edges, source, destination)
        ); // true
    }

    // Check whether a path exists from source to destination
    public static boolean validPath(
            int n, int[][] edges, int source, int destination) {

        // 1. Create an empty adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 2. Add undirected edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 3. Track visited nodes
        boolean[] visited = new boolean[n];

        // 4. Start DFS from source
        return dfs(source, destination, graph, visited);
    }

    private static boolean dfs(
            int node,
            int destination,
            List<List<Integer>> graph,
            boolean[] visited) {

        // Base case: destination reached
        if (node == destination) {
            return true;
        }

        // Mark current node as visited
        visited[node] = true;

        // Explore all unvisited neighbors
        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {

                // If any recursive call finds the destination,
                // propagate true back to the caller
                if (dfs(neighbor, destination, graph, visited)) {
                    return true;
                }
            }
        }

        // Destination not reachable through this branch
        return false;
    }
}