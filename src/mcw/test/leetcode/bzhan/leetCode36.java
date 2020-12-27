package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\14 0014-16:32
 * valid suduko
 */
public class leetCode36 {
    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return false;
        //check each row
        for (int row = 0; row < 9; row++) {
            boolean[] taken = new boolean[9];
            for (int idx = 0; idx < 9; idx++) {
                char c = board[row][idx];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num]) return false;
                    else taken[num] = true;
                }
            }
        }

        //check each column
        for (int col = 0; col < 9; col++) {
            boolean[] taken = new boolean[9];
            for (int idx = 0; idx < 9; idx++) {
                char c = board[idx][col];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num]) return false;
                    else taken[num] = true;
                }
            }
        }

        //check each 3 * 3 box
        for (int box = 0; box < 9; box++) {
            boolean[] taken = new boolean[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + 3 * (box / 3)][(col + 3 * (box % 3))];
                    if (c != '.') {
                        int num = c - '1';
                        if (taken[num]) return false;
                        taken[num] = true;
                    }
                }
            }
        }
        return true;
    }
}
