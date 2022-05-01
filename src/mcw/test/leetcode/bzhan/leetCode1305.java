package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 *
 * @author MCW 2022/5/1
 */
public class leetCode1305 {

    /**
     * 中序遍历 + 归并
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        //中序遍历 获取 二叉搜索树的 有序集合
        inorder(root1, nums1);
        inorder(root2, nums2);

        List<Integer> merged = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while (true) {
            //如果 树1 遍历完成，将 树2 剩下的节点 加入到 集合中
            if (p1 == nums1.size()) {
                merged.addAll(nums2.subList(p2, nums2.size()));
                break;
            }
            //如果 树2 遍历完成，将 树1 剩下的节点 加入到 集合中
            if (p2 == nums2.size()) {
                merged.addAll(nums1.subList(p1, nums1.size()));
                break;
            }
            if (nums1.get(p1) < nums2.get(p2)) {
                merged.add(nums1.get(p1++));
            } else {
                merged.add(nums2.get(p2++));
            }
        }
        return merged;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node != null) {
            inorder(node.left, res);
            res.add(node.val);
            inorder(node.right, res);
        }
    }
}
