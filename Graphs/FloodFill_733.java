public class FloodFill_733{
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if(originalColor == color){
            return image;
        }
        dfs(sr, sc, image, originalColor, color);
        return image;
    }

    public void dfs(int row, int col, int[][] image, int originalColor, int color){

        if( row<0 || row>=image.length || col<0 || col>=image[0].length){
            return;
        }
        if(image[row][col] != originalColor){
            return;
        }

        image[row][col] = color;

        dfs(row-1,col,image,originalColor,color); //up
        dfs(row+1,col,image,originalColor,color);//down
        dfs(row,col-1,image,originalColor,color);//left
        dfs(row,col+1,image,originalColor,color);//right




    }
}

