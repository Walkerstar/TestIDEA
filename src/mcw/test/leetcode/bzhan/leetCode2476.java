package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2476. 二叉搜索树最近节点查询
 * <p>
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 * <p>
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 * <p>
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 返回数组 answer 。
 * <p>
 * 示例 1 ：
 * <p>
 * 输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * 输出：[[2,2],[4,6],[15,-1]]
 * 解释：按下面的描述找出并返回查询的答案：
 * - 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
 * - 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
 * - 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。
 * <p>
 * 示例 2 ：
 * <p>
 * 输入：root = [4,null,9], queries = [3]
 * 输出：[[-1,4]]
 * 解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [2, 10^5] 内
 * 1 <= Node.val <= 10^6
 * n == queries.length
 * 1 <= n <= 10^5
 * 1 <= queries[i] <= 10^6
 *
 * @author MCW 2024/2/24
 */
public class leetCode2476 {


    /**
     * 方法一：二分查找
     * 思路与算法
     * <p>
     * 根据题意要求给定查询的值 queries_i，需要在二叉搜索中找到 min_i,max_i：
     * <p>
     * min_i是树中小于等于 queries_i的最大值。如果不存在这样的值，则使用 −1 代替。
     * max_i是树中大于等于 queries_i的最小值。如果不存在这样的值，则使用 −1 代替。
     * <p>
     * 由于该二叉搜索树并不是平衡的，则最坏情况该树可能形成一条链，直接在树上查找可能存在超时。
     * 我们可以保存树中所有的节点值，并将其排序，每次给定查询值 val 时，利用二分查找直接在树中找到大于等于 val 的最小值与小于等于 val 的最小值。
     * <p>
     * 由于给定的二叉树为二叉搜索树，因此按照中序遍历该树的结果即为升序，我们直接用数组 arr 保存二叉树的中序遍历结果，
     * 并使用二分查找在索引中找到大于等于 val 最左侧的索引 index，此时分析如下：
     * <p>
     * 如果索引 index 合法存在，则此时大于等于 val 的最小元素即为 arr[index]，否则则为 −1，
     * 如果此时 arr[index]=val，则小于等于 val 的最大元素也为 arr[index]。
     * <p>
     * 如果索引 index 大于 000，则此时小于等于 val 的最大元素即为 arr[index−1]，否则则为 −1。
     * <p>
     * 我们按照题目要求返回查询结果即可。
     */
    public List<List<Integer>> closesNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        dfs(root, arr);
        List<List<Integer>> res = new ArrayList<>();
        for (int val : queries) {
            int maxVal = -1, minVal = -1;
            int idx = binarySearch(arr, val);
            if (idx != arr.size()) {
                maxVal = arr.get(idx);
                if (arr.get(idx) == val) {
                    minVal = val;
                    List<Integer> list = new ArrayList<>();
                    list.add(minVal);
                    list.add(maxVal);
                    res.add(list);
                    continue;
                }
            }
            if (idx > 0) {
                minVal = arr.get(idx - 1);
            }
            List<Integer> list2 = new ArrayList<>();
            list2.add(minVal);
            list2.add(maxVal);
            res.add(list2);
        }
        return res;
    }


    public void dfs(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        dfs(root.left, arr);
        arr.add(root.val);
        dfs(root.right, arr);
    }


    public int binarySearch(List<Integer> arr, int target) {
        int low = 0, high = arr.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


}
