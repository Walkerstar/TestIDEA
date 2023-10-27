package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 1465. 切割后面积最大的蛋糕
 * 中等
 * <p>
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中：
 * <p>
 * horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * <p>
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
 * 由于答案可能是一个很大的数字，因此需要将结果 对 10^9 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * <p>
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 * <p>
 * 示例 2：
 * <p>
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 * <p>
 * 示例 3：
 * <p>
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 * <p>
 * 提示：
 * <p>
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length <= min(h - 1, 10^5)
 * 1 <= verticalCuts.length <= min(w - 1, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * 题目数据保证 horizontalCuts 中的所有元素各不相同
 * 题目数据保证 verticalCuts 中的所有元素各不相同
 *
 * @author MCW 2023/10/27
 */
public class leetCode1465 {

    /**
     * 方法一：贪心
     * 思路与算法
     * <p>
     * 题目给出一个高度为 h 长度为 w 的矩形蛋糕，我们需要按照水平切割方案 horizontalCuts，和竖直切割方案 verticalCuts 对蛋糕进行切割，
     * 其中 horizontalCuts[i] 表示从矩形蛋糕顶部水平往下距离 horizontalCuts[i] 的位置进行切割，
     * verticalCuts[j] 表示从矩形蛋糕最左侧往右距离 verticalCuts[j] 的位置进行切割。
     * 现在我们需要求出切割后的面积最大的蛋糕面积对 10^9 + 7 取模后的值。
     * <p>
     * 首先，我们需要将水平切割和竖直切割的位置数组 horizontalCuts 和 verticalCuts 进行排序，
     * 并且在数组的开头添加 0 和结尾添加对应的矩形边界值。
     * 这是为了确保我们考虑到所有的切割位置，包括矩形的边缘。
     * 然后在排序后的切割位置数组中，我们可以计算相邻切割位置之间的间隔，以找出水平和竖直切割的最大间隔。
     * 因为每个间隔代表了一块蛋糕的尺寸，水平和竖直间隔的乘积就是对应蛋糕块的面积，所以最大面积由最大水平间隔和最大竖直间隔相乘得到。
     * 最后我们返回最大面积对 10^9 + 7 的取模即可。
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        return (int) ((long) calMax(horizontalCuts, h) * calMax(verticalCuts, w) % 1000000007);
    }

    public int calMax(int[] arr, int boarder) {
        int res = 0, pre = 0;
        for (int i : arr) {
            res = Math.max(i - pre, res);
            pre = i;
        }
        return Math.max(res, boarder - pre);
    }
}
