package Other;

/**
 * 给定一个 n * m 的二维矩阵，每个位置都是字符，
 * U、D、L、R表示传送带的位置，会被传送到：上、下、左、右，
 * ’.‘ 、’O‘ 分别表示空地、目标，一定只有一个目标点，
 * 可以在空地上选择上、下、左、右四个方向的一个，
 * 到达传送带的点会被强制移动到其他指向的下一个位置，
 * 如果越界直接结束，返回有几个点可以到达’O‘点
 *
 * @author MCW 2023/7/2
 */
public class WhereCanReachNumber {
    public int number(char[][] map) {

        int n = map.length;
        int m = map[0].length;
        boolean[][] visited = new boolean[n][m];
        // queue[i] = { 行坐标，列坐标}
        int[][] queue = new int[n * m][2];
        int l = 0;
        int r = 0;
        int ans = 0;
        // 找到 O 在哪，目的地
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'O') {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                    break;
                }
            }
        }

        // [] [] [] [] [] [] [] [] ...
        // l .........r.....
        // 队列里还有位置
        while (l < r) {
            ans++;
            int[] cur = queue[l++];
            int row = cur[0];
            int col = cur[1];

            // 如果 当前点的上方的点的位置未超过边界， 且 未访问过 ，且 当前点 的上方 是 ’D‘ 或者 ’.‘ ，
            // 那么就说明 可以通过上方的点 来到当前点，所以将上方的点 加入队列
            if (row - 1 >= 0 && !visited[row - 1][col] && (map[row - 1][col] == 'D' || map[row - 1][col] == '.')) {
                visited[row - 1][col] = true;
                queue[r][0] = row - 1;
                queue[r++][1] = col;
            }

            // 如果 当前点的下方的点的位置未超过边界， 且 未访问过 ，且 当前点 的下方 是 ’U‘ 或者 ’.‘ ，
            // 那么就说明 可以通过下方的点 来到当前点，所以将下方的点 加入队列
            if (row + 1 < n && !visited[row + 1][col] && (map[row + 1][col] == 'U' || map[row + 1][col] == '.')) {
                visited[row + 1][col] = true;
                queue[r][0] = row + 1;
                queue[r++][1] = col;
            }

            // 如果 当前点的左方的点的位置未超过边界， 且 未访问过 ，且 当前点 的左方 是 ’R‘ 或者 ’.‘ ，
            // 那么就说明 可以通过左方的点 来到当前点，所以将左方的点 加入队列
            if (col - 1 >= 0 && !visited[row][col - 1] && (map[row][col - 1] == 'R' || map[row][col - 1] == '.')) {
                visited[row][col - 1] = true;
                queue[r][0] = row;
                queue[r++][1] = col - 1;
            }

            // 如果 当前点的右方的点的位置未超过边界， 且 未访问过 ，且 当前点 的右方 是 ’L‘ 或者 ’.‘ ，
            // 那么就说明 可以通过右方的点 来到当前点，所以将右方的点 加入队列
            if (col + 1 < m && !visited[row][col + 1] && (map[row][col + 1] == 'L' || map[row][col + 1] == '.')) {
                visited[row][col + 1] = true;
                queue[r][0] = row;
                queue[r++][1] = col + 1;
            }
        }
        return ans;
    }
}
