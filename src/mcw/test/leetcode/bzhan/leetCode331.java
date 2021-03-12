package mcw.test.leetcode.bzhan;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 验证二叉树的前序序列化
 *
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，
 * 我们可以使用一个标记值记录，例如 #。
 *
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 * @author mcw 2021\3\12 0012-15:14
 */
public class leetCode331 {

    /**
     * 方法一：栈
     * 我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
     *
     * 二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
     *   1.如果遇到了空节点，则要消耗一个槽位；
     *   2.如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
     *
     * 此外，还需要将根节点作为特殊情况处理。
     *
     * 我们使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
     * 当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。无论何时，
     * 如果栈顶元素变为 0，就立刻将栈顶弹出。
     *
     * 遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列；否则若栈不为空，则序列不合法。此外，在遍历的过程中，
     * 若槽位数量不足，则序列不合法。
     */
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                //读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 回顾方法一的逻辑，如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。
     *
     * 因此，我们可以只维护一个计数器，代表栈中所有元素之和，其余的操作逻辑均可以保持不变。
     */
    public boolean isValidSerialization1(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            } else {
                //读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots ++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }

    /**
     * 二叉树叶子节点数 = 度为2的节点数 + 1
     */
    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int ans = 1;
        if (preorder.charAt(0) == '#') {
            return n == 1;
        }
        for (int i = 1; i < n - 1; i++) {
            if (preorder.charAt(i) == ',') {
                if (preorder.charAt(i + 1) != '#') {
                    ++ans;
                } else {
                    --ans;
                }
            }
            if (ans < 0 && i != n - 2) {
                return false;
            }
        }
        return ans == -1;
    }
}
