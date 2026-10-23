public class NumOfEnclaves_1020 {

    public int numEnclaves(int[][] grid) {

        int m=grid.length;
        int n=grid[0].length;
        int count=0;
        boolean[][] visited = new boolean[m][n];


        //left boundary --> col==0

        for(int row=0;row<m;row++){
            if(grid[row][0] == 1 && !visited[row][0]){
                dfs(grid,row,0,visited);
            }
        }
        //right --> col==n-1
        for(int row=0;row<m;row++){
            if(grid[row][n-1] == 1 && !visited[row][n-1]){
                dfs(grid,row,n-1,visited);
            }
        }
        //top
        for(int col=0;col<n;col++){
            if(grid[0][col] == 1 && !visited[0][col]){
                dfs(grid,0,col,visited);
            }
        }
        //bottom
        for(int col=0;col<n;col++){
            if(grid[m-1][col] == 1 && !visited[m-1][col]){
                dfs(grid,m-1,col,visited);
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(visited[i][j]==false && grid[i][j] ==1){
                    count++;
                }
            }
        }
        return count;

    }
    private static void dfs(int[][] grid,int row, int col, boolean[][] visited){

        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length){
            return ;
        }

        if(grid[row][col] == 0 || visited[row][col]){
            return;
        }
        visited[row][col] = true;

        dfs(grid,row-1,col,visited);
        dfs(grid,row+1,col,visited);
        dfs(grid,row,col-1,visited);
        dfs(grid,row,col+1,visited);

    }
}