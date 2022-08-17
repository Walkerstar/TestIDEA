package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * @author mcw 2022/8/17 11:42
 */
public class leetCode1302 {

    /**
     * 深度优先
     */
    int sum = 0;
    int maxDeep = -1;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode root, int currentDeep) {
        if (root == null) {
            return;
        }
        if (currentDeep > maxDeep) {
            maxDeep = currentDeep;
            sum = root.val;
        } else if (currentDeep == maxDeep) {
            sum += root.val;
        }
        dfs(root.left, currentDeep + 1);
        dfs(root.right, currentDeep + 1);
    }

    public int deepestLeavesSumBFS(TreeNode root) {
        Queue<TreeNode> deque = new ArrayDeque<>();
        //添加节点到队列尾部
        deque.offer(root);
        int sum = 0;

        //逐层遍历二叉树
        while (!deque.isEmpty()) {
            //计算每一层的节点和
            sum = 0;
            int size = deque.size();
            //遍历队列中的所有元素
            for (int i = 0; i < size; i++) {
                //删除队列头部元素，并返回
                TreeNode node = deque.poll();
                sum += node.val;
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //[1,2,3,4,5,null,6,7,null,null,null,null,8]
        TreeNode root=new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left= left;
        TreeNode right = new TreeNode(3);
        root.right= right;

        TreeNode left1 = new TreeNode(4);
        left.left= left1;
        left.right=new TreeNode(5);
        left1.left=new TreeNode(7);

        TreeNode right1 = new TreeNode(6);
        right.right= right1;
        right1.right=new TreeNode(8);

        System.out.println(new leetCode1302().deepestLeavesSum(root));
        System.out.println(new leetCode1302().deepestLeavesSumBFS(root));
    }
}
