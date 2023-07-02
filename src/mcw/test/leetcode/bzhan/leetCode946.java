package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 pushed 和 popped 两个序列，
 * 每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * <p>
 * 提示：
 * <p>
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 *
 * @author MCW 2023/7/2
 */
public class leetCode946 {

    /**
     * 根据上述分析，验证栈序列的模拟做法如下：
     * <p>
     * 1. 遍历数组 pushed，将 pushed 的每个元素依次入栈；
     * <p>
     * 2. 每次将 pushed 的元素入栈之后，如果栈不为空且栈顶元素与 popped 的当前元素相同，则将栈顶元素出栈，同时遍历数组 popped，直到栈为空或栈顶元素与 popped 的当前元素不同。
     * <p>
     * 遍历数组 pushed 结束之后，每个元素都按照数组 pushed 的顺序入栈一次。如果栈为空，则每个元素都按照数组 popped 的顺序出栈，返回 true。如果栈不为空，则元素不能按照数组  popped 的顺序出栈，返回  false。
     */
    public boolean validateStackSequence(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 不使用额外的空间
     */
    public boolean validateStackSequence2(int[] pushed, int[] popped) {
        int n = pushed.length;
        // 利用栈的大小
        // 以 pushed[ 前面 size 个空间 ] 为栈
        int size = 0;

        // i：那个位置个数， j：在弹出的数组中，要对比的下标
        for (int i = 0, j = 0; i < n; i++) {
            // 遍历 pushed 数组，依次将 待压入的数字 压入到 栈中
            pushed[size++] = pushed[i];
            // 栈不为空时，判断 栈顶元素 与 给定的 弹出数组 的顺序是否一致
            while (size > 0 && j < n && pushed[size - 1] == popped[j]) {
                size--;
                j++;
            }
        }
        return size == 0;
    }
}
