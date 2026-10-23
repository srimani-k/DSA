import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
//        List<List<Integer>> edges = List.of(
//                List.of(0, 1),
//                List.of(0, 2),
//                List.of(1, 3),
//                List.of(2, 3)
//        );

        List<List<Integer>> edges = List.of(
                List.of(0, 1),
                List.of(1, 2),
                List.of(2, 0),
                List.of(2, 3)
        );
        System.out.println(topo(edges, 4));
    }
    private static List<Integer> topo(List<List<Integer>> edges,int vertices){

        //given edges, create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<vertices;i++){
            adjList.add(new ArrayList<>());
        }
        for(List<Integer> edge : edges){
            int u = edge.get(0);
            int v = edge.get(1);

            adjList.get(u).add(v);
        }
        //calculate indegree of every node
        int[] indegree = new int[vertices];
        for(int i=0;i<adjList.size();i++){
            for(int j : adjList.get(i)){
                indegree[j]++;
            }
        }
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            result.add(node);

            for(int neighbor : adjList.get(node)){
                indegree[neighbor]--;

                if(indegree[neighbor] == 0){
                    q.offer(neighbor);
                }
            }
        }

        if(result.size() != vertices){
            return new ArrayList<>();
        }
        return result;
    }
}
