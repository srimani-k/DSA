import java.util.*;

public class AllPathsSourceTarget {

    public static void main(String[] args) {

        int[][] graph = {
                {1, 2},
                {3},
                {3},
                {}
        };

        List<List<Integer>> result =
                allPathsSourceTarget(graph);

        System.out.println(result);
        // [[0, 1, 3], [0, 2, 3]]
    }

    public static List<List<Integer>> allPathsSourceTarget(
            int[][] graph) {

        // 1. Store all completed paths
        List<List<Integer>> allPaths = new ArrayList<>();

        // 2. Store the path currently being explored
        List<Integer> currentPath = new ArrayList<>();

        // 3. Destination is the last node
        int destination = graph.length - 1;

        // 4. Start DFS from node 0
        dfs(0, graph, currentPath, allPaths, destination);

        return allPaths;
    }

    private static void dfs(
            int node,
            int[][] graph,
            List<Integer> currentPath,
            List<List<Integer>> allPaths,
            int destination) {

        // 1. Add current node to the path
        currentPath.add(node);

        // 2. If destination reached, save a copy
        if (node == destination) {

            allPaths.add(new ArrayList<>(currentPath));

        } else {

            // 3. Explore every outgoing neighbor
            for (int neighbor : graph[node]) {

                dfs(
                        neighbor,
                        graph,
                        currentPath,
                        allPaths,
                        destination
                );
            }
        }

        // 4. Backtrack: remove current node
        // Restore the path before returning to the caller
        currentPath.remove(currentPath.size() - 1);
    }
}