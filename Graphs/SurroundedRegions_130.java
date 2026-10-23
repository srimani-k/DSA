public class SurroundedRegions_130 {

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int m=board.length;
        int n=board[0].length;
        //left boundary of board-> col==0
        for(int row=0;row<board.length;row++){
            if(board[row][0] == 'O'){
                dfs(board,row,0);
            }
        }
        //right boundary -> col==n-1
        for(int row=0;row<m;row++){
            if(board[row][n-1] == 'O'){
                dfs(board,row,n-1);
            }
        }
        //top -> r==0
        for(int col=0;col<n;col++){
            if(board[0][col] == 'O'){
                dfs(board,0,col);
            }
        }
        //botton -> r=m-1
        for(int col=0;col<n;col++){
            if(board[m-1][col] == 'O'){
                dfs(board,m-1,col);
            }
        }

        //

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }
    private void dfs(char[][] board,int row, int col){

        if(row<0 || row>=board.length || col<0 || col>= board[0].length){
            return; //out of the board
        }
        if(board[row][col] == 'X'){
            return; //we need to check only 'O'
        }
        if(board[row][col] == '#'){
            return;
        }

        board[row][col] = '#';

        dfs(board,row-1,col);
        dfs(board,row+1,col);
        dfs(board,row,col-1);
        dfs(board,row,col+1);

    }
    // private void dfs(char[][] board,int row, int col){

    //     if(row<0 || row>=board.length || col<0 || col>= board[0].length){
    //         return; //out of the board
    //         //and at edges
    //     }
    //     if(board[row][col] == 'X'){
    //         return; //we need to check only 'O'
    //     }
    //     if()
    //     // if(row==0 || row==board.length-1 || col==0 || col==board[0].length){
    //     //     return; //at edges
    //     // }

    //     board[row][col] = 'X';

    //     dfs(board,row-1,col);
    //     dfs(board,row+1,col);
    //     dfs(board,row,col-1);
    //     dfs(board,row,col+1);

    // }
}