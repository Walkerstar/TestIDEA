package mcw.test.leetcode.bzhan;

/**
 * First Missing Positive(返回 第一次 缺失 的 正数)
 * {1,2,0} return 3.        / {3,4,-1,1} return 2.
 * @author mcw 2020\5\15 0015-13:33
 */
public class leetCode41 {
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        //将所有小于等于 0 的数转换为 最大值, 确保这个数组为 正数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }

        //遍历数组，如果当前数小于数组长度，将 nums[该数的值-1] 变为 负数
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        //再次遍历数组，找出第一个大于0的数，返回 该数的索引 + 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
    }
}
