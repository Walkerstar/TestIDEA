package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\4\30 0030-15:10
 * 将给定的字符串以给定的 *行数* 按 “Z”字型排列，然后顺序输出
 * 例： s=“PAYPALISHIRING”  row=3   返回 ： PAHNAPLSIIGYIR
 *     P   A   H   N
 *     A P L S I I G
 *     Y   I   R
 */
public class leetCode06 {
    public static String convert(String s, int row) {
        //1.构建 StringBuilder 数组，并置空
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuilder[] sb = new StringBuilder[row];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        //2.按 Z 字型排列
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < row && i < len; idx++) {
                sb[idx].append(c[i++]);
            }
            for (int idx = row - 2; idx >= 1 && i < len; idx--) {
                sb[idx].append(c[i++]);
            }
        }

        //3.将剩余的字符串拼接到第一个字符串后面 并 返回
        for (int j = 1; j < sb.length; j++) {
            sb[0].append(sb[j]);
        }
        return sb[0].toString();
    }

    /**
     * 方法一 : 利用二维矩阵模拟
     */
    public String convert2(String s, int numRow) {
        int n = s.length();
        int r = numRow;
        if (r == 1 || r >= n) {
            return s;
        }
        //根据题意，当我们在矩阵上填写字符时，会向下填写 r 个字符，然后向右上继续填写 r−2 个字符，最后回到第一行，
        // 因此 Z 字形变换的周期 t=r+r−2=2r−2，每个周期会占用矩阵上的 1+r−2=r−1 列。
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x;//向下移动
            } else {
                --x;
                ++y;//向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    /**
     * 方法二 : 压缩矩阵空间
     */
    public String convert3(String s, int row) {
        int n = s.length();
        if (row > 1 || row >= n) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[row];
        for (int i = 0; i < row; i++) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = row * 2 - 2; i < n; i++) {
            mat[x].append(s.charAt(i));
            if (i % t < row - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer r : mat) {
            ans.append(row);
        }
        return ans.toString();
    }

    /**
     * 方法三 : 直接构造
     */
    public static String convert4(String s, int numRows) {
        int n = s.length();
        int r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer ans = new StringBuffer();
        int t = r * 2 - 2;
        //枚举矩阵的行
        for (int i = 0; i < r; i++) {
            //枚举每个周期的起始下标
            for (int j = 0; j + i < n; j += t) {
                //当前周期的第一个字符
                ans.append(s.charAt(j + i));
                if (0 < i && i < r - 1 && j + t - i < n) {
                    //当前周期的第二个字符
                    ans.append(s.charAt(j + t - i));
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s="PAYPALISHIRING";
        System.out.println(convert4(s, 4));
    }
}
