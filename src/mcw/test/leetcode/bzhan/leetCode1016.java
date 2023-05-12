package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回 false 。
 * <p>
 * 子字符串 是字符串中连续的字符序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0110", n = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "0110", n = 4
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] 不是 '0' 就是 '1'
 * 1 <= n <= 10^9
 *
 * @author MCW 2023/5/11
 */
public class leetCode1016 {

    /**
     * 方法一：数学 + 滑动窗口 + 哈希表
     * 思路与算法
     * <p>
     * 题目给定一个二进制字符串  s 和一个正整数  n。我们需要判断  1 到  n 中的每一个整数是否都是  s 的子字符串。
     * <p>
     * 设  [l,r] 表示大于等于  l 且小于等于  r 范围内的整数，对于  n>1，一定存在  k ∈ N+(上标 +) ，使得 2^k  ≤ n < 2^(k+1) 。
     * 那么对于 [ 2^(k-1)  , 2^k - 1  ] 内的数，它们都小于 n，且二进制表示都为 k 位，
     * 所以字符串 s 中至少需要有 2^(K-1)  个长度为 k 的不同二进制数。
     * <p>
     * 记字符串 s 的长度为 m，则 m 至少需要满足： m ≥ 2^(K-1) + K - 1
     * <p>
     * 同理在 [2^K ,n] 内的数，二进制表示都为 k+1 位，所以 m 同样需要满足： m ≥ n − 2^K + K +1
     * <p>
     * 若上述两个条件不满足，则可以直接返回 False，否则，因为题目给定 m ≤ 1000，所以此时 k 一定不大于 11。
     * 又因为若 s 的子串能包含 [ 2^(k-1) , 2^k - 1 ] 全部二进制表示，则一定可以包含 [ 1 , 2^k - 1 ]全部二进制表示。
     * 因为我们可以将 [ 2^(k-1) , 2^k - 1 ] 中数的二进制表示中的最高位的 1 去掉并去掉对应的前导零，便可以得到 [ 0,2^(k-1) - 1 ]的全部二进制表示。
     * <p>
     * 比如若当前 s 的子串能包含 [4,7] 全部的二进制表示，即有子串中有 100,101,110,111，
     * 那么去掉每一个数的最高位和前导零可以得到 [0,3] 的全部二进制表示 0,1,10,11。
     * <p>
     * 所以我们对字符串 s 判断是否存在 [ 2^(k-1)  , 2^k - 1 ] 和 [ 2^k , n ]的全部二进制表示即可。我
     * 们可以分别用长度为 k 和 k+1 的「滑动窗口」来枚举 s 中全部长度为 k 和 k+1 的子串，将其加入哈希表，
     * 并判断哈希表中是否存在 [ 2^(k-1) , n ] 中的全部数即可。
     * <p>
     * 以上的分析都在 n > 1 的基础上，当 n = 1 时，我们只需要判断 ‘1’ 是否在 s 中即可。
     */
    public boolean queryString(String s, int n) {
        if (n == 1) {
            return s.indexOf('1') != -1;
        }
        int k = 30;
        while ((1 << k) >= n) {
            --k;
        }
        if (s.length() < (1 << k - 1) + k - 1 || s.length() < n - (1 << k) + k + 1) {
            return false;
        }
        return help(s, k, 1 << (k - 1), (1 << k) - 1) && help(s, k + 1, 1 << k, n);
    }

    public boolean help(String s, int k, int mi, int ma) {
        Set<Integer> set = new HashSet<>();
        int t = 0;
        for (int i = 0; i < s.length(); i++) {
            t = t * 2 + (s.charAt(i) - '0');
            if (i >= k) {
                t -= (s.charAt(i - k) - '0') << k;
            }
            if (i > k - 1 && t >= mi && t <= ma) {
                set.add(t);
            }
        }
        return set.size() == ma - mi + 1;
    }

    /**
     * 暴力
     */
    public boolean queryString2(String s, int n) {
        for (int i = 0; i < n; i++) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }

}
