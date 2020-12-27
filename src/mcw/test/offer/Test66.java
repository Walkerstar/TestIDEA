package mcw.test.offer;

/**
 * @author mcw 2020\1\28 0028-17:54
 *
 * 地上有一个m行和n列的方格，一个机器人从（0,0）开始移动，每次一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子
 * 例如 k=18 时，不能进入（35,38） 因为3+5+3+8=19
 */
public class Test66 {

    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int cnt = 0;
    private int rows;
    private int cols;
    private int threshold;
    private int[][] digitSum;

    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];
        dfs(marked, 0, 0);
        return cnt;
    }

    private void initDigitSum() {
        int[] digitSumOne = new int[Math.max(rows, cols)];
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.digitSum[i][j] = digitSumOne[i] + digitSumOne[j];
            }
        }
    }

    private void dfs(boolean[][] marked, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c])
            return;
        marked[r][c] = true;
        if (this.digitSum[r][c] > this.threshold)
            return;
        cnt++;
        for (int[] n : next) {
            dfs(marked, r + n[0], c + n[1]);
        }
    }


    //另一种方法
    public int movingCount1(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return countingSteps(threshold, rows, cols, 0, 0, visited);
    }

    private int countingSteps(int threshold, int rows, int cols, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] || bitSum(r) + bitSum(c) > threshold)
            return 0;
        visited[r][c] = true;
        return  countingSteps(threshold, rows, cols, r - 1, c, visited) +
                countingSteps(threshold, rows, cols, r + 1, c, visited) +
                countingSteps(threshold, rows, cols, r, c - 1, visited) +
                countingSteps(threshold, rows, cols, r, c + 1, visited) + 1;

    }

    private int bitSum(int t) {
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;
    }

}
