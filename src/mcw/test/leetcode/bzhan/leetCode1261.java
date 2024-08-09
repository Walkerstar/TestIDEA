package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1261. 在受污染的二叉树中查找元素
 * <p>
 * 给出一个满足下述规则的二叉树：
 * <p>
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 * <p>
 * 请你先还原二叉树，然后实现 FindElements 类：
 * <p>
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["FindElements","find","find"]
 * [[[-1,null,-1]],[1],[2]]
 * 输出：
 * [null,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1]);
 * findElements.find(1); // return False
 * findElements.find(2); // return True
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * ["FindElements","find","find","find"]
 * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
 * 输出：
 * [null,true,true,false]
 * 解释：
 * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True
 * findElements.find(3); // return True
 * findElements.find(5); // return False
 * <p>
 * 示例 3：
 * <p>
 * 输入：
 * ["FindElements","find","find","find","find"]
 * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
 * 输出：
 * [null,true,false,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
 * findElements.find(2); // return True
 * findElements.find(3); // return False
 * findElements.find(4); // return False
 * findElements.find(5); // return True
 * <p>
 * <p>
 * 提示：
 * <p>
 * TreeNode.val == -1
 * 二叉树的高度不超过 20
 * 节点的总数在 [1, 10^4] 之间
 * 调用 find() 的总次数在 [1, 10^4] 之间
 * 0 <= target <= 10^6
 *
 * @author MCW 2024/3/12
 */
public class leetCode1261 {

    /**
     * 方法一：深度优先搜索 + 哈希表
     * 对二叉树的根节点 root 进行深度优先搜索，在搜索过程中，根据规则对遍历的节点的值进行恢复，
     * 并且将遍历的节点的值加入哈希表 valSet 中。调用 find 函数时，返回 target 值是否存在哈希表 valSett 中。
     */
    static class FindElements {
        public Set<Integer> valSet;

        public FindElements(TreeNode root) {
            this.valSet = new HashSet<>();
            dfs(root, 0);
        }

        public boolean find(int target) {
            return valSet.contains(target);
        }

        public void dfs(TreeNode node, int val) {
            if (node == null) {
                return;
            }
            node.val = val;
            valSet.add(val);
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
        }
    }


    /**
     * 方法二：深度优先搜索 + 位运算
     */
    static class FindElements2 {
        private TreeNode root;

        public FindElements2(TreeNode root) {
            dfs(root, 0);
            this.root = root;
        }

        public boolean find(int targer) {
            targer++;
            int k = 30 - Integer.numberOfLeadingZeros(targer);
            TreeNode node = root;
            while (k >= 0 && node != null) {
                if ((targer & (1 << k)) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                k--;
            }
            return node != null;
        }

        private void dfs(TreeNode node, int val) {
            if (node == null) {
                return;
            }
            node.val = val;
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
        }
    }
}
