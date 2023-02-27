package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * <p>
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * <p>
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * <p>
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 *
 * @author mcw 2023/2/27 10:57
 */
public class leetCode1144 {

    /**
     * 现在我们需要求得将 nums 转换为「锯齿数组」所需的最小操作次数。不失一般性，我们设我们最后求得的「锯齿数组」满足每一个偶数索引对应的元素都大于其相邻的元素。
     * 因为操作的先后并不会影响最终结果，所以我们若我们要对某些偶数下标的元素进行操作，则先完成该些操作，然后再统一对奇数下标的元素进行操作，
     * 设数组 p 为对 nums 某些偶数下标的元素进行操作后的数组，那么为了使数组 p 为满足每一个偶数索引对应的元素都大于其相邻的元素的「锯齿数组」，
     * 其奇数下标的元素都需要小于其相邻元素的最小值，即为了使某一个奇数下标位置 i 满足要求的最少操作次数 ci = max(p[i]−q(i)+1,0) ，
     * 其中 q(i) 表示数组 p 中位置 i 相邻元素的最小值，因为若我们对某个偶数下标的元素进行了操作，则该元素相邻的奇数下标元素所需要的操作次数只增不减，
     * 但是总的操作次数一定增加了，所以最优解中一定不会存在对偶数下标操作的情况。那么我们对 nums 中每一个奇数位置 i 的 ci 求和,
     * 即为此时求每一个偶数索引对应的元素都大于其相邻的元素的「锯齿数组」的最少操作的次数。
     * 对于求每一个奇数索引对应的元素都大于其相邻的元素的「锯齿数组」的最小操作次数同理，最终返回两者的较小值即可。
     */
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    public int help(int[] nums, int pos) {
        int res = 0;
        for (int i = pos; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }

    public int moveToMakeZigzag2(int[] nums) {
        int[] s = new int[2];
        for (int i = 0, n = nums.length; i < nums.length; i++) {
            int left = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
            int right = i < n - 1 ? nums[i + 1] : Integer.MAX_VALUE;
            s[i % 2] += Math.max(nums[i] - Math.min(left, right) + 1, 0);
        }
        return Math.min(s[0], s[1]);
    }
}
