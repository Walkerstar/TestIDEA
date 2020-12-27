package mcw.test.leetcode.bzhan;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域.格子是边长为 1 的正方形。
 * 网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * @author mcw 2020/10/30 10:34
 */
public class leetCode463 {

    public int islandPerimeter(int[][] grid){
        int n= grid.length;
        int m=grid[0].length;
        int ans=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]==1){
                    ans+=dfs(i,j,grid,n,m);
                }
            }
        }
        return ans;
    }

    private int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x<0 || x>=n || y<0 || y>=m || grid[x][y]==0){
            return 1;
        }
        if (grid[x][y]==2){
            return 0;
        }
        //标记已走过
        grid[x][y]=2;
        return dfs(x+1,y,grid,n,m)+dfs(x-1,y,grid,n,m)+dfs(x,y-1,grid,n,m)+dfs(x,y+1,grid,n,m);

    }


    public int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rsp = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    rsp += 4;
                    //重点关注前面遍历过得方格，如果之前有相邻方格，就-2;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        rsp -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        rsp -= 2;
                    }
                }
            }
        }
        return rsp;
    }


    public int islandPerimeter1(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    return dfs1(grid,i,j);
                }
            }
        }
        return 0;
    }

    private int dfs1(int[][] grid, int i, int j) {
        // 从一个岛屿方格走向网格边界，周长加 1
        if (!(0<=i && i< grid.length && 0<=j && j<grid[0].length)){
            return 1;
        }
        // 从一个岛屿方格走向水域方格，周长加 1
        if (grid[i][j]==0){
            return 1;
        }
        if (grid[i][j]!=1){
            return 0;
        }
        grid[i][j]=2;
        return dfs1(grid,i-1,j)+dfs1(grid,i+1,j)+dfs1(grid,i,j-1)+dfs1(grid,i,j+1);
    }
}
