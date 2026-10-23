public class NumOfIslands_200 {
    public int numIslands(char[][] grid) {

        int countIslands=0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // Check grid[row][col]
                if(grid[row][col]== '1'){
                    countIslands++;
                    dfs(grid,row,col);
                }
            }}
        return countIslands;

    }

    private static void dfs(char[][] grid, int row, int col){

        //stop/base conditions
        if(row<0 || row>=grid.length ||col<0 ||col>=grid[0].length){
            return;
        }

        //when reached water...dont go check neighbors
        if(grid[row][col] == '0') {
            return;
        }

        grid[row][col] ='0';

        // Explore all four directions
        dfs(grid, row - 1, col); // Up
        dfs(grid, row + 1, col); // Down
        dfs(grid, row, col - 1); // Left
        dfs(grid, row, col + 1); // Right

    }
}