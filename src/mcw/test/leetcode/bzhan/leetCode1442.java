package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 arr 。
 * <p>
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * <p>
 * a 和 b 定义如下：
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * @author mcw 2021/5/18 15:16
 */
public class leetCode1442 {

    /**
     * 方法一：三重循环<p>
     * 计算数组 arr 的异或前缀和 S，枚举符合 0≤ i< j ≤ k < n 的下标 i，j 和 k，统计满足等式 S_i=S_{k+1}的三元组个数。
     */
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    if (s[i] == s[k + 1]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：二重循环
     * <p>
     * 当等式 S_i=S_{k+1}成立时，[i+1,k] 的范围内的任意 j 都是符合要求的，对应的三元组个数为 k−i。因此我们只需枚举下标 i 和 k。
     */
    public int countTriplets1(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (s[i] == s[k + 1]) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }

    /**
     * 哈希表
     */
    public int countTriplets2(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> total = new HashMap<>();
        int ans = 0;
        for (int k = 0; k < n; k++) {
            if (cnt.containsKey(s[k + 1])) {
                ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + 1);
        }
        return ans;
    }
}
