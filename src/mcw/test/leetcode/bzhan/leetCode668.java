package mcw.test.leetcode.bzhan;

/**
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * <p>
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第 k 小的数字。
 * <p>
 * m 和 n 的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 *
 * @author MCW 2022/5/18
 */
public class leetCode668 {

    /**
     * 二分查找
     * 求第几小等价于求有多少数字不超过 x。
     * 我们可以遍历乘法表的每一行，对于乘法表的第 i 行，其数字均为 i 的倍数，因此不超过 x 的数字有 min(⌊ x/i ⌋,n) 个，
     */
    public static int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            //计算中间数
            int x = left + (right - left) / 2;

            // (x/n) * n 可以求出 x 之前 完整行的 数的个数，即 在 x 之前 有 (x/n) 行, n 列 的数字
            // 例如：m=3,n=3, x=5 ,则 (x / n) * n= (5 / 3) * 3 = 1 * 3,
            int count = x / n * n;

            // 从 (x/n) 行, n 列 的下一行开始遍历，
            // 即 当前行号是 (x/n)+1 ,继续统计剩余行中不超过 x 的数字的个数
            for (int i = x / n + 1; i <= m; i++) {
                count += x / i;
            }
            if (count >= k) {
                right = x;
            } else {
                left = x + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        //System.out.println(findKthNumber(3, 3, 6));
    }

}
