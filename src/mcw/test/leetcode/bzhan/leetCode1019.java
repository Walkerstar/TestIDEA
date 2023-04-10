package mcw.test.leetcode.bzhan;

import mcw.test.common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个长度为 n 的链表 head
 * <p>
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 * <p>
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
 * 如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 * <p>
 * 输入：head = [2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * <p>
 * 提示：
 * <p>
 * 链表中节点数为 n
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 *
 * @author mcw 2023/4/10 10:37
 */
public class leetCode1019 {

    /**
     * 方法一：单调栈
     * 思路与算法
     * <p>
     * 找出「下一个更大的元素」是经典的可以用单调栈解决的问题。
     * <p>
     * 我们对链表进行一次遍历，同时维护一个内部值单调递减（不是严格单调递减，可以相等）的栈。
     * 栈中的元素对应着还没有找到下一个更大的元素的那些元素，它们在栈中的顺序与它们在链表中出现的顺序一致。
     * 这也解释了为什么栈中的值是单调递减的：
     * 如果有两个元素不满足单调递减的限制，那么后一个元素大于前一个元素，与「还没有找到下一个更大的元素」相矛盾。
     * <p>
     * 当我们遍历到链表中的值为 val 的节点时，只要它大于栈顶元素的值，
     * 我们就可以不断取出栈顶的节点，即栈顶节点的下一个更大的元素就是 val。
     * 在这之后，我们再将 val 放入栈顶，为其在后续的遍历中找到它的下一个更大的元素，同时也保证了栈的单调性。
     * <p>
     * 细节
     * <p>
     * 当我们取出栈顶的元素时，我们是不知道它在链表中的位置的。因此在单调栈中，我们需要额外存储一个表示位置的变量。
     */
    public int[] nexLargerNodes(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        Deque<int[]> stack = new ArrayDeque<>();

        ListNode cur = head;
        int idx = -1;
        while (cur != null) {
            ++idx;
            ans.add(0);
            while (!stack.isEmpty() && stack.peek()[0] < cur.val) {
                ans.set(stack.poll()[1], cur.val);
            }
            stack.push(new int[]{cur.val, idx});
            cur = cur.next;
        }
        int size = ans.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }
}
