package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给你一个下标从 0 开始的二维整数数组 nums 。一开始你的分数为 0 。你需要执行以下操作直到矩阵变为空：
 * <p>
 * 1.矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。
 * 2.在步骤 1 删除的所有数字中找到最大的一个数字，将它添加到你的 分数 中。
 * 请你返回最后的 分数。
 * <p>
 * 示例 1：
 * 输入：nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
 * 输出：15
 * 解释：第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。所以总得分为 7 + 5 + 3 = 15 。
 * <p>
 * 示例 2：
 * 输入：nums = [[1]]
 * 输出：1
 * 解释：我们删除 1 并将分数增加 1 ，所以返回 1 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 300
 * 1 <= nums[i].length <= 500
 * 0 <= nums[i][j] <= 10^3
 *
 * @author MCW 2023/7/4
 */
public class leetCode2679 {

    /**
     * 方法一：模拟
     * 思路与算法
     * <p>
     * 设矩阵的行与列的数目分别为 m,n，题目要求每次选择每一行中的最大数并删除，每次操作的得分为删除数中的最大值，'
     * 因此我们可以利用「大堆」进行模拟即可，具体过程如下：
     * <p>
     * 1. 将每一行的元素都加入到一个「堆」中，设第 i 行加入到优先队列 pq[i]，堆顶元素即为当前行中的最大值；
     * 2. 每次删除时，删除每一行的最大值即堆顶元素，用 maxVal 记录当前删除元素的最大值，此时即可得到当前删除时的得分；
     * 3. 根据提议可以知道每次删除时都会删除掉每一行中的一个元素，一共需要 n 次删除即可将矩阵中的所有元素删除完。
     * 4. 最终返回所有删除得分之和即可；
     */
    public int matrixSum(int[][] nums) {
        int res = 0;
        int m = nums.length;
        int n = nums[0].length;
        PriorityQueue<Integer>[] pq = new PriorityQueue[m];
        for (int i = 0; i < m; i++) {
            pq[i] = new PriorityQueue<>((a, b) -> b - a);
            for (int j = 0; j < n; j++) {
                pq[i].offer(nums[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            int maxVal = 0;
            for (int k = 0; k < m; k++) {
                maxVal = Math.max(maxVal, pq[k].poll());
            }
            res += maxVal;
        }
        return res;
    }

    /**
     * 方法二：排序
     * 思路与算法
     * <p>
     * 由于每次删除操作中，每行删除的元素即为当前行中的最大值，因此我们可以直接将每行的元素按照从大到小排序，
     * 然后按照列遍历矩阵，每次删除操作得分即为当前列的最大值，因此最终得分即为所有列中的最大值之和。
     */
    public int matrixSum2(int[][] nums) {
        int res = 0;
        int m = nums.length;
        int n = nums[0].length;
        for (int[] ints : nums) {
            Arrays.sort(ints);
        }
        for (int j = 0; j < n; j++) {
            int maxVal = 0;
            for (int[] num : nums) {
                maxVal = Math.max(maxVal, num[j]);
            }
            res += maxVal;
        }
        return res;
    }
}
