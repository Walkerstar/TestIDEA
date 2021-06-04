package mcw.test.leetcode.bzhan;

/**
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * <p>
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 *
 * @author mcw 2021/5/28 11:21
 */
public class leetCode477 {


    /**
     * 对于数组 nums 中的某个元素 val，若其二进制的第 i 位为 1，我们只需统计 nums 中有多少元素的第 i 位为 0，即计算出了 val 与
     * 其他元素在第 i 位上的汉明距离之和。
     * <p>
     * 具体地，若长度为 n 的数组 nums 的所有元素二进制的第 i 位共有 c 个 1，n−c 个 0，则些元素在二进制的第 i 位上的汉明距离之和为
     * c*(n−c),我们可以从二进制的最低位到最高位，逐位统计汉明距离。将每一位上得到的汉明距离累加即为答案。
     * <p>
     * 具体实现时，对于整数 val 二进制的第 i 位，我们可以用代码 (val >> i) & 1 来取出其第 i 位的值。此外，由于 10^9<2^30 ，
     * 我们可以直接从二进制的第 0 位枚举到第 29 位。
     */
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < 30; i++) {
            int c = 0;
            for (int num : nums) {
                c += (num >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }
}
