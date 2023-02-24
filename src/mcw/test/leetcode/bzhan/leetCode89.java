package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
 * 1.每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
 * 2.第一个整数是 0
 * 3.一个整数在序列中出现 不超过一次
 * 4.每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 5.第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * <p>
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 16
 *
 * @author mcw 2023/2/23 15:20
 */
public class leetCode89 {


    public List<Integer> grayCode(int n, int start) {
        List<Integer> ret = new ArrayList<>();
        ret.add(start);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(ret.get(j) | (1 << (i - 1)));
            }
        }
        return ret;
    }

    /**
     * 公式法
     * 第 i ( i ≥ 0 ) 个格雷码即为：gi = i ⊕ ⌊ i/2 ⌋
     *
     */
    public List<Integer> grayCode2(int n, int start) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }
}
