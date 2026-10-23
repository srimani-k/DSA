
import java.util.*;

class IsGraphBipartite_785 {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        // -1 means uncolored; 0 and 1 are the two colors
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // Handle disconnected components
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0, graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int node, int currentColor,
                        int[][] graph, int[] color) {

        color[node] = currentColor;

        for (int neighbor : graph[node]) {

            // Neighbor is uncolored: assign the opposite color
            if (color[neighbor] == -1) {
                if (!dfs(neighbor, 1 - currentColor, graph, color)) {
                    return false;
                }
            }
            // Adjacent nodes cannot have the same color
            else if (color[neighbor] == currentColor) {
                return false;
            }
        }

        return true;
    }
}
