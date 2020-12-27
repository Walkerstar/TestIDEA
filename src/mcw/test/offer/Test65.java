package mcw.test.offer;

/**
 * @author mcw 2020\1\28 0028-17:00
 *
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可从任意一个格子开始。每次一步，已经过的格子不可再进入
 */
public class Test65 {

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows <= 0 || cols <= 0 || str == null)
            return false;

        //用于将当前路径上的访问过的点标记为“已访问”，防止同一个点访问两次 ,默认false，表示未访问
        boolean[] marked = new boolean[matrix.length];

        //所有点都作为起点搜索一次
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathTo(matrix, rows, cols, row, col, str, 0, marked)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathTo(char[] matrix, int rows, int cols, int row, int col, char[] str, int len, boolean[] marked) {

        //由于用一维数组表示二维矩阵，第row行第col列，就是row*cols+col
        int index = row * cols + col;
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[index] != str[len] || marked[index]) {
            return false;
        }

        //递归深度能到字符串末尾，说明有这条路径
        if (len == str.length - 1)
            return true;

        marked[index] = true;

        //四个方向上没有可以到达下一个字符的路径，有任意一个方向可以就继续下一个字符的搜索
        if (
                hasPathTo(matrix, rows, cols, row, col - 1, str, len + 1, marked)  ||
                hasPathTo(matrix, rows, cols, row, col + 1, str, len + 1, marked)  ||
                hasPathTo(matrix, rows, cols, row - 1, col, str, len + 1, marked) ||
                hasPathTo(matrix, rows, cols, row + 1, col, str, len + 1, marked)  )
        {
            return true;
        }

        //对于搜索失败需要回溯的路径上的点，则要重新标记为“未访问”，方便另辟蹊径时能访问到
        marked[index]=false;
        return false;
    }

    public static void main(String[] args) {
        String s="abcesfcsadee";
        char[] chars = s.toCharArray();
        String str="bcced";
        char[] chars1 = str.toCharArray();
        System.out.println(hasPath(chars,3,4,chars1));
    }
}
