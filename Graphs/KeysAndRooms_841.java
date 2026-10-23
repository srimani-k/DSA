import java.util.*;

public class KeysAndRooms_841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];

        dfs(visited, 0, rooms);

        for (boolean ans : visited) {
            if (ans == false) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(boolean[] visited, int node,
                            List<List<Integer>> rooms) {

        visited[node] = true;

        for (int neighbor : rooms.get(node)) {
            if (!visited[neighbor]) {
                dfs(visited, neighbor, rooms);
            }
        }
    }
}