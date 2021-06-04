package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * <p>
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * <p>
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * @author mcw 2021/5/29 16:47
 */
public class leetCode1074 {

    /**
     * 前缀和 + 哈希表
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length, n = matrix[0].length;
        // 枚举上边界
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            // 枚举下边界
            for (int j = i; j < m; j++) {
                for (int c = 0; c < n; c++) {
                    // 更新每列的元素和
                    sum[c] += matrix[j][c];
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    private int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, pre = 0;
        for (int x : nums) {
            pre += x;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
