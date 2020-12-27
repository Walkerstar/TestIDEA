package mcw.test.leetcode.niuke;

import java.util.Base64;

/**
 * @author mcw 2020\3\8 0008-20:31
 *
 * 现在有一个仅包含“X” 和 “O"的二维板，请捕获所有的被”X“包围的区域。
 *
 * 捕获一个被包围区域的方法是将被包围区域中的所有“O” 变成 “X”
 * 例：输入  X X X X    输出: X X X X
 *          X O O X          X X X X
 *          X X O X          X X X X
 *          X 0 X X          X O X X

 */
public class Test21 {
    public int rowNum=0;
    public int cloNum=0;

    public void solve(char[][] board){
        if(board==null || board.length<=0 || board[0].length<=0)
            return;
        rowNum=board.length;
        cloNum=board[0].length;

        /**
         * 遍历四条边上的 O ,并深度遍历与其相连的 O ,将这些 O 都转为 *
         */
        for (int i = 0; i < cloNum; i++) {
            dfs(board,0,i);
            dfs(board,rowNum-1,i);
        }
        for (int i = 0; i < rowNum; i++) {
            dfs(board,i,0);
            dfs(board,i,cloNum-1);
        }

        /**
         * 将剩余的 O 都变为 X ， 将剩余的 * 都变为 O
         */
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < cloNum; j++) {
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < cloNum; j++) {
                if(board[i][j]=='*'){
                    board[i][j]='O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if(board[row][col]=='O'){
            board[row][col]='*';
            if(row>1)        dfs(board,row-1,col);
            if(col>1)        dfs(board,row,col-1);
            if(row<rowNum-1) dfs(board,row+1,col);
            if(col<cloNum-1) dfs(board,row,col+1);
        }
    }
}
