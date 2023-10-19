package mcw.test.leetcode.bzhan;

import java.util.HashMap;

/**
 * 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。
 * 其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * nums 中的所有元素 互不相同
 *
 * @author MCW 2023/10/19
 */
public class leetCode1726 {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        var cnt = new HashMap<Integer, Integer>();
        // 统计每个乘积出现的次数
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int key = nums[i] * nums[j];
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }
        }
        int ans = 0;
        for (int v : cnt.values()) {
            // 假设存在 n 组数，对于其中任意两组数 a,b 和 c,d，均满足 a×b=c×d 的条件，
            // 则这样的组合一共有 C²n = n×(n−1) / (2×1) 个。
            // 因为该元组可以按照不同顺序组合，组成 8 个不同的元组，
            // 所以 可以构成的同积元组的数目为：C²n × 8 = n × (n-1) × 4
            ans += v * (v - 1) * 4;
        }
        return ans;
    }
}
