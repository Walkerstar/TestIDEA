package mcw.test.leetcode.bzhan;

/**
 * 1793. 好子数组的最大分数
 * <p>
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 * <p>
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
 * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 * <p>
 * 请你返回 好 子数组的最大可能 分数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^4
 * 0 <= k < nums.length
 *
 * @author MCW 2024/3/19
 */
public class leetCode1793 {

    /**
     * 方法一：双指针
     * 由于好子数组必须要包含 nums[k]，那么我们可以使用两个指针 left 和 right 表示选择的子数组为 (left,right)（左开右开），
     * 且 left 和 right 的初始值为 k−1 和 k+1。
     * <p>
     * 随后我们可以枚举子数组分数定义中 min{⋯} 部分的值。它的最大值为 nums[k]，最小值为数组 nums 中的最小值。
     * 随后我们从大到小进行枚举，当枚举到 i 时，我们可以不断向左移动 left，或者向右移动 right，直到：
     * <p>
     * 指针超出数组的边界，或者
     * 指针指向的元素小于 i，分数定义中的 min{⋯} 的值发生了变化。
     * <p>
     * 当移动完成后，(left,right) 就是最小值大于等于 i 的一个子数组，它的分数至少为：(right−left−1)×i
     * <p>
     * 当 i 恰好是 (left,right) 的最小值时，上式就是它对应的分数。当 i 继续减少但指针没有移动时，上式计算出的分数会比正确的分数要低，但一定不会更高。因此，只要我们在枚举的过程中维护上式的最大值，就可以得到正确的答案。
     * <p>
     * 当两个指针都超出数组的边界时，就可以结束枚举并返回答案。
     */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int ans = 0;
        for (int i = nums[k]; ; --i) {
            while (left >= 0 && nums[left] >= i) {
                --left;
            }
            while (right < n && nums[right] >= i) {
                ++right;
            }
            ans = Math.max(ans, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
        }
        return ans;
    }


    /**
     * 方法二：优化的双指针
     * 思路与算法
     * <p>
     * 我们可以对方法一中的代码进行优化。
     * <p>
     * 方法一效率较低的原因是在 i 比 (left,right) 中的最小值更小，但指针没有移动时，计算出的分数是没有意义的。
     * 指针没有移动的原因是 nums[left] 和 nums[right] 都小于 i，因此我们应当直接把 i 减少至二者的较大值，而不是每次减少 1，
     * 这样就可以保证每一次循环中都至少会移动一次指针，就可以将 C 从时间复杂度中移除。
     * <p>
     * 细节
     * <p>
     * 在减少 i 时，需要注意指针已经超出数组边界的情况。
     */
    public int maximumScore2(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int ans = 0;
        for (int i = nums[k]; ; ) {
            while (left >= 0 && nums[left] >= i) {
                --left;
            }
            while (right < n && nums[right] >= i) {
                ++right;
            }
            ans = Math.max(ans, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
            i = Math.max((left == -1 ? -1 : nums[left]), (right == n ? -1 : nums[right]));
            if (i == -1) {
                break;
            }
        }
        return ans;
    }
}
