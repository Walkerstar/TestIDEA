package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1379. 找出克隆二叉树中的相同节点
 * <p>
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * <p>
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * <p>
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * <p>
 * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: tree = [7,4,3,null,null,6,19], target = 3
 * 输出: 3
 * 解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
 * <p>
 * 示例 2:
 * <p>
 * 输入: tree = [7], target =  7
 * 输出: 7
 * <p>
 * 示例 3:
 * <p>
 * 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * 输出: 4
 * <p>
 * 提示：
 * <p>
 * 树中节点的数量范围为 [1, 104] 。
 * 同一棵树中，没有值相同的节点。
 * target 节点是树 original 中的一个节点，并且不会是 null 。
 * <p>
 * <p>
 * 进阶：如果树中允许出现值相同的节点，将如何解答？
 *
 * @author MCW 2024/4/3
 */
public class leetCode1379 {

    /**
     * 方法一：深度优先搜索
     * 同时对二叉树 original 与 cloned 进行深度优先搜索，如果 original 当前搜索的节点的引用等于 target 节点的引用，
     * 那么对应地返回 cloned 当前搜索的节点，否则继续对各自的左右节点同时进行搜索。
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

    /**
     * 方法二：广度优先搜索
     * 使用队列同时对二叉树 original 和 cloned 进行广度优先搜索，
     * 初始时分别将根节点 original 和 cloned 压入队列 q1 和 q2。
     * 假设当前搜索的节点分别为 node1 与 node2  ，将 node1 与 node2分别弹出队列，
     * 如果 node1 节点的引用等于 target 节点的引用，那么返回 node2，
     * 否则将分别将 node1 和 node2的非空子节点压入队列 q1 和 q2 ，继续搜索过程。
     */
    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();

        q1.offer(original);
        q2.offer(cloned);

        while (q1.size() > 0) {
            TreeNode node1 = q1.poll(), node2 = q2.poll();
            if (node1 == target) {
                return node2;
            }
            if (node1.left != null) {
                q1.offer(node1.left);
                q2.offer(node2.left);
            }
            if (node1.right != null) {
                q1.offer(node1.right);
                q2.offer(node2.right);
            }
        }
        return null;// impossible case


    }
}
