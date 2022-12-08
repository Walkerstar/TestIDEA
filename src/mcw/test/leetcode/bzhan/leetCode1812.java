package mcw.test.leetcode.bzhan;

/**
 * 给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。
 * <p>
 * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
 * 给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。
 * <p>
 * coordinates.length == 2
 * 'a' <= coordinates[0] <= 'h'
 * '1' <= coordinates[1] <= '8'
 *
 * @author mcw 2022/12/8 14:30
 */
public class leetCode1812 {

    /**
     * 经过观察可以发现，从左下角开始，棋盘的行数和列数（均从 1 开始计数）之和如果为奇数，则为白色格子，如果和为偶数，则为黑色格子。
     * 可以根据这个结论判断格子颜色。
     */
    public static boolean squareIsWithe(String coordinates) {
        return ((coordinates.charAt(0) - 'a' + 1) + (coordinates.charAt(1) - '0')) % 2 == 1;
    }

    /**
     * 异或
     * 白色 0 ，黑色 1
     */
    public static boolean squareIsWithe2(String coordinates) {
        //a列：白色  b列：黑色 c列：白色 .....
        int letter = (coordinates.charAt(0) - 'a') % 2;
        //1行:黑色 2行:白色 3行:黑色 .....
        int number = (coordinates.charAt(1) - '0') % 2;
        return (letter ^ number) == 0;
    }

    public static void main(String[] args) {
        System.out.println(squareIsWithe("g3"));
        System.out.println(squareIsWithe2("h8"));
    }
}
