package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * (可参考 offer/Test59)
 * @author mcw 2020/12/22 19:47
 */
public class leetCode103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if (root==null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue=new LinkedList<>();
        nodeQueue.offer(root);
        //表明从左至右
        boolean isOrderLeft=true;

        while (!nodeQueue.isEmpty()){
            Deque<Integer> levelList=new LinkedList<>();
            //记录二叉树每层的数量
            int size=nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode=nodeQueue.poll();
                //如果从左至右，我们每次将被遍历到的元素插入至双端队列的末尾
                if (isOrderLeft){
                    levelList.offerLast(curNode.val);
                }else {
                    //如果从右至左，我们每次将被遍历到的元素插入至双端队列的头部。
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left!=null){
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right!=null){
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft=!isOrderLeft;
        }
        return ans;
    }
}
