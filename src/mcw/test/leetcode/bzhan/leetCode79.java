package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\30 0030-16:44
 * Word Serch 参考 offer 中 Test65
 */
public class leetCode79 {
    public static boolean exist(char[][] board, String word) {
        if (board == null) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (existHelper(board, used, word.toCharArray(), 0, col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean existHelper(char[][] board, boolean[][] used, char[] word, int len, int col, int row) {
        if (len == word.length) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if (used[row][col] || board[row][col] != word[len]) return false;
        used[row][col] = true;
        if (
                existHelper(board, used, word, len + 1, col + 1, row)  ||
                        existHelper(board, used, word, len + 1, col - 1, row)  ||
                        existHelper(board, used, word, len + 1, col, row + 1) ||
                        existHelper(board, used, word, len + 1, col, row - 1) )
        { return true; }

        used[row][col] = false;
        return false;
    }
}
