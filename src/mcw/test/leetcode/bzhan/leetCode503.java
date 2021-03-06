package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是
 * 按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * @author mcw 2021\3\6 0006-15:55
 */
public class leetCode503 {

    /**
     * 单调栈 + 循环数组:
     *
     * .单调栈中保存的是下标，从栈底到栈顶的下标在数组 nums 中对应的值是单调不升的。
     * .每次我们移动到数组中的一个新的位置 ii，我们就将当前单调栈中所有对应值小于 nums[i] 的下标弹出单调栈，这些值的下一个
     * 更大元素即为 nums[i]（证明很简单：如果有更靠前的更大元素，那么这些位置将被提前弹出栈）。随后我们将位置 i 入栈。
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }
}
