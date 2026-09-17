import java.util.*;
public class Graph{
//            0 -- 1
//            |    |
//            2    3
//            |    |
//            4 ---
//                    0 → [1, 2]
//                    1 → [0, 3]
//                    2 → [0, 4]
//                    3 → [1, 4]
//                    4 → [2, 3]
//   edges:   0 -- 1
//            0 -- 2
//            1 -- 3
//            2 -- 4
//            3 -- 4

    public static void main(String[] args){
        int vertices=5;

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<vertices;i++){
            graph.add(new ArrayList<>());
        }
        //graph is empty right now
        //add edges
        addEdge(graph,0,1);
        addEdge(graph,0,2);
        addEdge(graph,1,3);
        addEdge(graph,2,4);
        addEdge(graph,3,4);

        //print graph
        for(int i=0;i<vertices;i++){
            System.out.println(i+" -> "+graph.get(i));
        }


    }

    private static void addEdge(List<List<Integer>> g, int u, int v){
        g.get(u).add(v);
        g.get(v).add(u);
    }

    private static void bfs(List<List<Integer>> graph, int start){
        //what we need - 1.visited arr, 2. create a queue
        boolean[] visited =  new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        //3. put starting vertex into the q, mark it as visited
        q.offer(start);
        visited[start] = true;

        //4.
        while(!q.isEmpty()){
            //a. remove front from q
            int current = q.poll();

            //b.process/print current
            System.out.println(current);

            //c.look at all neighbors -->traverse list of vertex
            for(int neighbor : graph.get(current)){

                // Only visit a neighbor if we haven't visited it before
                if(!visited[neighbor]){
                    visited[neighbor]=true;   //mark it as visited
                    q.offer(neighbor);        // add it to the q for later processing
                }
            }

        }
    }
    private static void dfs(List<List<Integer>> graph, int current, boolean[] visited){
        visited[current] = true;

        System.out.println(current);

        for (int neighbor : graph.get(current)) {

            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    private static boolean cycleDetection(List<List<Integer>> graph,int current, int parent, boolean[] visited){
        visited[current] = true;

        for(int neighbor : graph.get(current)){
            if(visited[neighbor]){
                if(neighbor!=parent){
                    return true;
                }
            }else{
                if (cycleDetection(graph, neighbor, current, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int countComponents(List<List<Integer>> graph){
        boolean[] visited = new boolean[graph.size()];

        int count=0;

        for(int i=0;i<graph.size();i++){
            if(!visited[i]){
                count++;
                dfs(graph,i,visited);
            }
        }
        return count;
    }

    private static boolean cycleDetectiondisconnectedGraph(List<List<Integer>> graph){
        boolean[] visited = new boolean[graph.size()];

        for(int i=0;i<graph.size();i++){
            if(!visited[i]){
                if (cycleDetection(graph, i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

}