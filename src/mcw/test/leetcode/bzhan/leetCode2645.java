package mcw.test.leetcode.bzhan;

/**
 * 2645. 构造有效字符串的最少插入数
 * <p>
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 * <p>
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "b"
 * 输出：2
 * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：word = "aaa"
 * 输出：6
 * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：word = "abc"
 * 输出：0
 * 解释：word 已经是有效字符串，不需要进行修改。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 50
 * word 仅由字母 "a"、"b" 和 "c" 组成。
 *
 * @author MCW 2024/1/11
 */
public class leetCode2645 {

    /**
     * 动态规划
     * <p>
     * 定义状态 d[i] 为将前 i 个字符（为了方便编码，下标从 1 开始）拼凑成若干个 abc 所需要的最小插入数。
     * 那么初始状态 d[0]=0，最终要求解 d[n]，其中 n 为 word 的长度。
     * <p>
     * 转移过程有以下几种情况：
     * <p>
     * 1. word[i] 单独存在于一组 abc 中，d[i]=d[i−1]+2。
     * 2. 如果 word[i]>word[i−1]，那么 word[i] 可以和 word[i−1] 在同一组 abc 中，d[i]=d[i−1]−1。
     * <p>
     * d[i] 取以上情况的最小值。在本题中，每个字符总是尽可能的与前面的字符去组合，
     * 因此情况 2 优于情况 1（从动态转移方程中也可以发现此规律），按照顺序依次更新 d[i] 即可，并不需要取最小值。
     */
    public int addMinimum(String word) {
        int n = word.length();
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 2;
            if (i > 1 && word.charAt(i - 1) > word.charAt(i - 2)) {
                d[i] = d[i - 1] - 1;
            }
        }
        return d[n];
    }

    /**
     * 方法二：直接拼接
     * 思路与算法
     * <p>
     * 方法一中提到，每个字符总是尽可能的与前面的字符去组合，
     * 如果当前字符大于前面字符（例如 c 前面是 a，或者 c 前面是 b），则很易于处理（中间添置一个 b，或者什么也不做)。
     * <p>
     * 相反，如果当前字符小于等于前面字符呢？
     * <p>
     * 其实也很好处理，当前字符小于等于前面字符说明它们一定不在同一组 abc 中，只需要添置若干字符过渡这两者即可。
     * 例如 b 前面是 c，则需要在中间添置 a，又例如 b 前面是 b，则需要在中间添置 ca。
     * <p>
     * 以上两种情况可以用一个模型来表示，设当前字符是 x，前面字符是 y，那么需要添置的字符个数为 (x−y−1+3) mod 3。其中 +3 再对 3 取模，可以应对 x 小于等于 y 的情况。
     * <p>
     * 最后还需要处理头尾两个字符，word[0] 前面添置 word[0]−‘a′ 个字符，word[n−1] 后面添置 ‘c′−word[n−1] 个字符。
     * 两个可以合并为 word[0]−word[n−1]+2。
     */
    public int addMinimum2(String word) {
        int n = word.length();
        int res = word.charAt(0) - word.charAt(n - 1) + 2;

        for (int i = 1; i < n; i++) {
            res += (word.charAt(i) - word.charAt(i - 1) + 2) % 3;
        }
        return res;
    }

    /**
     * 方法三：计算组数
     * 思路与算法
     * <p>
     * 方法二中提到，如果当前字符小于等于前面字符说明它们一定不在同一组中，反之则一定在同一组中。
     * 因此如果我们知道了最终的组数，就可以直接计算需要添加的字符数量。
     * 而最终的组数，就等于所有满足后者字符小于等于前者字符的情况数再加 1。
     */
    public int addMinimum3(String word) {
        int n = word.length();
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                cnt++;
            }
        }
        return cnt * 3 - n;
    }
}
