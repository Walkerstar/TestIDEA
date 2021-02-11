package mcw.test.leetcode.bzhan;

/**
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变    1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的：    对于数组中任意的    i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * @author mcw 2021/2/07 15:37
 */
public class leetcode665 {

    public boolean checkPoosiblity(int[] nums) {
        int n = nums.length, c = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                c++;
                if (c > 1) {
                    return false;
                }
                //若找到了一个满足 nums[i]>nums[i+1] 的 i，在修改 nums[i] 或 nums[i+1] 之后，还需要检查 nums 是否变成了非递减数列。
                //
                //我们可以将 nums[i] 修改成小于或等于 nums[i+1] 的数，但由于还需要保证 nums[i] 不小于它之前的数，nums[i] 越大越好，
                // 所以将 nums[i] 修改成 nums[i+1] 是最佳的；同理，对于 nums[i+1]，修改成 nums[i] 是最佳的。
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
}
