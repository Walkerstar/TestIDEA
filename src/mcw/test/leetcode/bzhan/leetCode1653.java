package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b' 。
 * <p>
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * <p>
 * 请你返回使 s 平衡 的 最少 删除次数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * 示例 2：
 * <p>
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'
 *
 * @author mcw 2023/3/6 10:25
 */
public class leetCode1653 {

    /**
     * 通过删除部分字符串，使得字符串达到下列三种情况之一，即为平衡状态：
     * <p>
     * 1.字符串全为 “a”；
     * 2.字符串全为 “b”；
     * 3.字符串既有 “a” 也有 “b”，且所有 “a” 都在所有 “a” 左侧。
     * 其中，为了达到第 1 种情况，最少需要删除所有的 “b”。
     * 为了达到第 2 种情况，最少需要删除所有的 “a”。
     * 而第 3 种情况，可以在原字符串相邻的两个字符之间划一条间隔，删除间隔左侧所有的 “b” 和间隔右侧所有的 “a” 即可达到。
     * 用 leftb 表示间隔左侧的 “b” 的数目，righta 表示间隔右侧的 “a” 的数目，leftb + righta 即为当前划分的间隔下最少需要删除的字符数。
     * 这样的间隔一共有 n−1 种，其中 n 是 s 的长度。遍历字符串 s，即可以遍历 n−1 种间隔，同时更新 leftb 和 righta 的数目。
     * 而上文讨论的前两种情况，其实就是间隔位于首字符前和末字符后的两种特殊情况，可以加入第 3 种情况一并计算。
     */
    public static int minimumDeletions(String s) {
        int leftb = 0, righta = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                righta++;
            }
        }
        int res = righta;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                righta--;
            } else {
                leftb++;
            }
            res = Math.min(res, leftb + righta);
        }
        return res;
    }

    /**
     * 由题目知道，最终删除完毕后最终形态是所有 ′a′ 应该在 ′b′ 之前。
     * 我们可以考虑动态规划思想，如果当前字符是′a′，那么以′a′ 结尾只需要考虑上一个′a′  结尾的长度加一。
     * 如果当前字符是′b′' ，需要考虑上一个字符是′a′或′b′  结尾长度中最大的长度加一，最终求出′a′或′b′ 长度最大值，删除元素就为总长减去这个值。
     */
    public static int minimumDeletions2(String s) {
        int a = 0, b = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                a++;
            } else {
                b = Math.max(a, b) + 1;
            }
        }
        return s.length() - Math.max(a, b);
    }

    /**
     * 另一种动态规划思想，我们遇到′b′  不考虑删除，遇到′a′ 考虑删除或者删除前面′b′ 的数量，每次取删除的最小值，最终结果就是要删除的最小数
     */
    public static int minimumDeletions3(String s) {
        int bCnt = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                ans = Math.min(ans + 1, bCnt);
            } else {
                ++bCnt;
            }
        }
        return ans;
    }

}
