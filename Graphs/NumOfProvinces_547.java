public class NumOfProvinces_547 {

    public int findCircleNum(int[][] isConnected) {
        int n= isConnected.length;
        boolean[] visited = new boolean[n];

        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
                dfs(isConnected,i,visited);
            }
        }
        return count;

    }

    private static void dfs(int[][] isConnected, int node, boolean[] visited){

        visited[node] = true;

        for(int neighbor=0; neighbor < isConnected.length; neighbor++){

            if(isConnected[node][neighbor]==1 && !visited[neighbor]){

                dfs(isConnected,neighbor,visited);

            }
        }
    }
}