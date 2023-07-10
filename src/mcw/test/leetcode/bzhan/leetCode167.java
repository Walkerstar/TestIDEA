package mcw.test.leetcode.bzhan;

/**
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * <p>
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * <p>
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * <p>
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * <p>
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * <p>
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * <p>
 * 提示：
 * <p>
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 *
 * @author MCW 2023/7/8
 */
public class leetCode167 {
    /**
     * 方法一：二分查找
     * 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。
     * 利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
     */
    public int[] towSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法二：双指针
     * 初始时两个指针分别指向第一个元素位置和最后一个元素的位置。
     * 每次计算两个指针指向的两个元素之和，并和目标值比较。
     * 如果两个元素之和等于目标值，则发现了唯一解。
     * 如果两个元素之和小于目标值，则将左侧指针右移一位。
     * 如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。
     * <p>
     * 使用双指针的实质是缩小查找范围。那么会不会把可能的解过滤掉？答案是不会。
     * 假设 numbers[i] + numbers[j] = target 是唯一解，其中  0 ≤ i < j ≤ numbers.length−1。
     * 初始时两个指针分别指向下标  0 和下标 numbers.length−1，左指针指向的下标小于或等于 i，右指针指向的下标大于或等于 j。
     * 除非初始时左指针和右指针已经位于下标 i 和 j，否则一定是左指针先到达下标  i 的位置或者右指针先到达下标 j 的位置。
     * <p>
     * 如果左指针先到达下标  i 的位置，此时右指针还在下标 j 的右侧， sum > target，因此一定是右指针左移，左指针不可能移到  i 的右侧。
     * <p>
     * 如果右指针先到达下标  j 的位置，此时左指针还在下标 i 的左侧， sum < target，因此一定是左指针右移，右指针不可能移到  j 的左侧。
     * <p>
     * 由此可见，在整个移动过程中，左指针不可能移到  i 的右侧，右指针不可能移到  j 的左侧，因此不会把可能的解过滤掉。
     * 由于题目确保有唯一的答案，因此使用双指针一定可以找到答案。
     */
    public int[] towSum2(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{-1, -1};
    }
}
