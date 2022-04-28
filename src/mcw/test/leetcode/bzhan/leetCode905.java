package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * <p>
 * 返回满足此条件的 任一数组 作为答案。
 *
 * @author mcw 2022/4/28 10:14
 */
public class leetCode905 {


    /**
     * 方法一：两次遍历
     * <p>
     * 新建一个数组 res 用来保存排序完毕的数组。遍历两次 nums，第一次遍历时把所有偶数依次追加到 res 中，第二次遍历时把所有奇数依次追加到 res 中。
     */
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int index = 0;
        int[] res = new int[n];
        for (int num : nums) {
            if (num % 2 == 0) {
                res[index++] = num;
            }
        }
        for (int num : nums) {
            if (num % 2 == 1) {
                res[index++] = num;
            }
        }
        return res;
    }

    /**
     * 方法二：双指针 + 一次遍历
     * 记数组 nums 的长度为 n。方法一需要遍历两次 nums，第一次遍历时遇到奇数会跳过，第二次遍历时遇到偶数会跳过，这部分可以优化。
     * <p>
     * 新建一个长度为 n 的数组 res 用来保存排完序的数组。遍历一遍 nums，遇到偶数则从 res 左侧开始替换元素，遇到奇数则从 res 右侧开始替换元素。
     * 遍历完成后，res 就保存了排序完毕的数组。
     */
    public int[] sortArrayByParity2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0;
        int right = n - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[left++] = num;
            } else {
                res[right--] = num;
            }
        }
        return res;
    }

    /**
     * 方法三：原地交换
     * <p>
     * 记数组 nums 的长度为 n。先从 nums 左侧开始遍历，如果遇到的是偶数，就表示这个元素已经排好序了，继续从左往右遍历，
     * 直到遇到一个奇数。然后从 nums 右侧开始遍历，如果遇到的是奇数，就表示这个元素已经排好序了，继续从右往左遍历，
     * 直到遇到一个偶数。交换这个奇数和偶数的位置，并且重复两边的遍历，直到在中间相遇，nums 排序完毕。
     */
    public int[] sortArrayByParity3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }
            while (left < right && nums[right] % 2 == 1) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
