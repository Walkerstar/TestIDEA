package mcw.test.leetcode.bzhan;

/**
 * 给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。
 * <p>
 * 如果满足以下条件，那么箱子是 "Bulky" 的：
 * 箱子 至少有一个 维度大于等于 104 。
 * 或者箱子的 体积 大于等于 109 。
 * 如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
 * 如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
 * 如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
 * 如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
 * 如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
 * 注意，箱子的体积等于箱子的长度、宽度和高度的乘积。
 * <p>
 * 示例 1：
 * <p>
 * 输入：length = 1000, width = 35, height = 700, mass = 300
 * 输出："Heavy"
 * 解释：
 * 箱子没有任何维度大于等于 104 。
 * 体积为 24500000 <= 109 。所以不能归类为 "Bulky" 。
 * 但是质量 >= 100 ，所以箱子是 "Heavy" 的。
 * 由于箱子不是 "Bulky" 但是是 "Heavy" ，所以我们返回 "Heavy" 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：length = 200, width = 50, height = 800, mass = 50
 * 输出："Neither"
 * 解释：
 * 箱子没有任何维度大于等于 104 。
 * 体积为 8 * 106 <= 109 。所以不能归类为 "Bulky" 。
 * 质量小于 100 ，所以不能归类为 "Heavy" 。
 * 由于不属于上述两者任何一类，所以我们返回 "Neither" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= length, width, height <= 10^5
 * 1 <= mass <= 10^3
 *
 * @author MCW 2023/10/20
 */
public class leetCode2525 {

    /**
     * 方法一：数学
     * 思路与算法
     * <p>
     * 题目给出箱子的三个维度长度和质量，要求返回一个表示箱子类别的字符串。
     * <p>
     * 首先，我们求出箱子最长的维度 maxd 和体积 vol。然后判断盒子：
     * <p>
     * 是否是 Heavy 的，即箱子的质量大于等于 100。
     * 是否是 Bulky 的，即最长的维度大于等于 10^4 或者箱子的体积大于等于 10^9。
     * 最后根据题意，返回对应的类别的字符串即可。
     */
    public String categorizeBox(int length, int width, int height, int mass) {
        long maxd = Math.max(length, Math.max(width, height));
        long vol = 1L * length * width * height;
        boolean isBulky = maxd >= 10000 || vol >= 1000000000;
        boolean isHeavy = mass >= 100;
        if (isBulky && isHeavy) {
            return "Both";
        } else if (isBulky) {
            return "Bulky";
        } else if (isHeavy) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }
}
