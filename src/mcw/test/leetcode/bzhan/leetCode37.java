package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\14 0014-15:39
 * Sudoku Solver(数独解算器)
 */
public class leetCode37 {
    public static void salveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        boolean tmp = salveSudokuHelper(board, 0, 0);
    }

    private static boolean salveSudokuHelper(char[][] board, int row, int col) {
        if (board == null || board.length != 9 || board[0].length != 9) return false;

        while (row < 9 && col < 9) {
            //find the empty cell
            if (board[row][col] == '.') {
                break;
            }
            if (col == 8) {
                col = 0;
                row++;
            } else col++;
        }
        //check out of bound after getting location
        if (row >= 9) return true;
        int newRow = row + col / 8;
        int newCol = (col + 1) % 9;
        for (int num = 1; num <= 9; num++) {//filled in with a number
            if (isValid(board, row, col, num)) {
                board[row][col] = (char) (num + '0');
                boolean result = salveSudokuHelper(board, newRow, newCol);
                if (result) return true;
                board[row][col] = '.';//backtracking(回溯)
            }
        }
        return false;
    }

    /**
     * 检验 每行每列 是否 存在 目标数
     */
    private static boolean isValid(char[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) { //check column and row
            if (board[row][i] == num + '0' || board[i][col] == num + '0') return false;
        }
        //check square 检查 3 * 3 的矩阵
        int rowoff = (row / 3) * 3;
        int coloff = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowoff + i][coloff + j] == num + '0') return false;
            }
        }
        return true;
    }

}
