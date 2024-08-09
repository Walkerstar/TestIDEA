package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 有一棵根节点为 0 的 家族树 ，总共包含 n 个节点，节点编号为 0 到 n - 1 。
 * 给你一个下标从 0 开始的整数数组 parents ，其中 parents[i] 是节点 i 的父节点。
 * 由于节点 0 是 根 ，所以 parents[0] == -1 。
 * <p>
 * 总共有 10^5 个基因值，每个基因值都用 闭区间 [1, 105] 中的一个整数表示。
 * 给你一个下标从 0 开始的整数数组 nums ，其中 nums[i] 是节点 i 的基因值，且基因值 互不相同 。
 * <p>
 * 请你返回一个数组 ans ，长度为 n ，其中 ans[i] 是以节点 i 为根的子树内 缺失 的 最小 基因值。
 * <p>
 * 节点 x 为根的 子树 包含节点 x 和它所有的 后代 节点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：parents = [-1,0,0,2], nums = [1,2,3,4]
 * 输出：[5,1,1,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树包含节点 [0,1,2,3] ，基因值分别为 [1,2,3,4] 。5 是缺失的最小基因值。
 * - 1：子树只包含节点 1 ，基因值为 2 。1 是缺失的最小基因值。
 * - 2：子树包含节点 [2,3] ，基因值分别为 [3,4] 。1 是缺失的最小基因值。
 * - 3：子树只包含节点 3 ，基因值为 4 。1是缺失的最小基因值。
 * <p>
 * 示例 2：
 * <p>
 * 输入：parents = [-1,0,1,0,3,3], nums = [5,4,6,2,1,3]
 * 输出：[7,1,1,4,2,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树内包含节点 [0,1,2,3,4,5] ，基因值分别为 [5,4,6,2,1,3] 。7 是缺失的最小基因值。
 * - 1：子树内包含节点 [1,2] ，基因值分别为 [4,6] 。 1 是缺失的最小基因值。
 * - 2：子树内只包含节点 2 ，基因值为 6 。1 是缺失的最小基因值。
 * - 3：子树内包含节点 [3,4,5] ，基因值分别为 [2,1,3] 。4 是缺失的最小基因值。
 * - 4：子树内只包含节点 4 ，基因值为 1 。2 是缺失的最小基因值。
 * - 5：子树内只包含节点 5 ，基因值为 3 。1 是缺失的最小基因值。
 * <p>
 * 示例 3：
 * <p>
 * 输入：parents = [-1,2,3,0,2,4,1], nums = [2,3,4,5,6,7,8]
 * 输出：[1,1,1,1,1,1,1]
 * 解释：所有子树都缺失基因值 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == parents.length == nums.length
 * 2 <= n <= 10^5
 * 对于 i != 0 ，满足 0 <= parents[i] <= n - 1
 * parents[0] == -1
 * parents 表示一棵合法的树。
 * 1 <= nums[i] <= 10^5
 * nums[i] 互不相同。
 *
 * @author MCW 2023/10/31
 */
public class leetCode2003 {


    /**
     * 方法一：深度优先搜索 + 启发式合并
     */
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        List<Integer>[] children = new List[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }
        int[] res = new int[n];
        Arrays.fill(res, 1);
        Set<Integer>[] geneSet = new Set[n];
        for (int i = 0; i < n; i++) {
            geneSet[i] = new HashSet<>();
        }
        dfs(0, res, nums, children, geneSet);
        return res;
    }

    public int dfs(int node, int[] res, int[] nums, List<Integer>[] children, Set<Integer>[] geneSet) {
        geneSet[node].add(nums[node]);
        for (int child : children[node]) {
            res[node] = Math.max(res[node], dfs(child, res, nums, children, geneSet));
            if (geneSet[node].size() < geneSet[child].size()) {
                Set<Integer> temp = geneSet[node];
                geneSet[node] = geneSet[child];
                geneSet[child] = temp;
            }
            geneSet[node].addAll(geneSet[child]);
        }
        while (geneSet[node].contains(res[node])) {
            res[node]++;
        }
        return res[node];
    }


    /**
     * 方法二：深度优先搜索
     * 根据题意，所有基因值互不相同，因此最多只有一个基因值为 1 的节点。
     * <p>
     * 首先，如果不存在基因值为 1 的节点，那么 1 就是以任一节点为根的子树缺失的最小基因值。
     * 如果存在基因值为 1 的节点，那么可以把所有节点分成两种类型：
     * <p>
     * ● 以该节点为根的子树不包含基因值为 1 的节点；
     * ● 以该节点为根的子树包含基因值为 1 的节点。
     * <p>
     * 对于第一种类型，类似地，1 就是以任一节点为根的子树缺失的最小基因值。
     * <p>
     * 对于第二种类型，这类节点是基因值为 1 的节点的祖先节点（充分必要条件）。
     * 沿用方法一的符号规定，对于某一节点 x 和任一它的祖先节点 y，都有 geneSet_x⊂geneSet_y。
     * 我们可以先找到基因值为 1 的节点，然后可以通过 parents 数组依次遍历它的祖先节点（类似于遍历链表）。
     * 在遍历过程中，我们对节点 node 执行一次深度优先搜索（使用 visited 来记录已经访问过的节点，避免重复搜索），
     * 将访问过的节点的基因值加入到集合 geneSet 中，然后不断枚举基因值（类似于方法一，枚举值为上一次枚举的缺失的最小基因值），
     * 直到找到节点 node 为根的子树缺失的最小基因值。
     * <p>
     * 因为依次遍历的节点为父子关系，所以可以通过 visited 来记录子节点已经搜索过的节点（
     * 这些节点的基因值已经加入到集合 geneSet 中），在父节点的搜索中可以跳过这些节点。
     */
    public int[] smallestMissingValueSubtree2(int[] parents, int[] nums) {
        int n = parents.length;
        List<Integer>[] children = new List[n];
        Arrays.fill(children, new ArrayList<Integer>());

        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }
        Set<Integer> geneSet = new HashSet<>();
        boolean[] visited = new boolean[n];
        int[] res = new int[n];
        Arrays.fill(res, 1);

        int iNode = 1, node = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                node = i;
                break;
            }
        }
        while (node != -1) {
            dfs2(node, nums, children, geneSet, visited);
            while (geneSet.contains(iNode)) {
                iNode++;
            }
            res[node] = iNode;
            node = parents[node];
        }
        return res;
    }

    public void dfs2(int node, int[] nums, List<Integer>[] children, Set<Integer> geneSet, boolean[] visited) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        geneSet.add(nums[node]);
        for (int child : children[node]) {
            dfs2(child, nums, children, geneSet, visited);
        }
    }

}
