import java.util.*;

public class NetworkConnected_1319 {

    public int makeConnected(int n, int[][] connections) {

        // now, check if no of connections >= n-1. Otherwise return -1.
        if(connections.length < n-1){
            return -1;
        }
        //adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        //GRAPH list contains 4 empty lists now
        for(int[] edge : connections){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int countOfComponents=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                countOfComponents++;
                dfs(i,graph,visited);
            }
        }
        //we have count of connected components now.
        //after 1st check, we know that no.of connection is obviously >= n-1

        //moves required = components-1
        return countOfComponents - 1;
    }

    private static void dfs(int node, List<List<Integer>> graph , boolean[] visited){
        visited[node]=true;

        for(int neighbor : graph.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor,graph,visited);
            }
        }
    }
}
