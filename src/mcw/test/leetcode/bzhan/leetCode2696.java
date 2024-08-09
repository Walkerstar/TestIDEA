package mcw.test.leetcode.bzhan;

import java.util.ArrayList;

/**
 * 2696. 删除子串后的字符串最小长度
 * <p>
 * 给你一个仅由 大写 英文字符组成的字符串 s 。
 * <p>
 * 你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。
 * <p>
 * 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。
 * <p>
 * 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ABFCACDB"
 * 输出：2
 * 解释：你可以执行下述操作：
 * - 从 "ABFCACDB" 中删除子串 "AB"，得到 s = "FCACDB" 。
 * - 从 "FCACDB" 中删除子串 "CD"，得到 s = "FCAB" 。
 * - 从 "FCAB" 中删除子串 "AB"，得到 s = "FC" 。
 * 最终字符串的长度为 2 。
 * 可以证明 2 是可获得的最小长度。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "ACBBD"
 * 输出：5
 * 解释：无法执行操作，字符串长度不变。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 仅由大写英文字母组成
 *
 * @author MCW 2024/1/10
 */
public class leetCode2696 {

    /**
     * 方法一：栈
     * <p>
     * 因为题目要求需要删除重新拼接的字符串产生的 “AB" 和 “CD" ，因此我们可以使用栈。
     * 当栈顶两个元素分别为 “AB" 或 “CD" 时，我们通过将它们弹出栈来模拟删除的过程。
     * 这样以来，后续的判断过程就是建立在已经删除元素的栈之上了。遍历完之后，返回栈的长度。
     */
    public int minLength(String s) {
        var stack = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.add(c);
            int m = stack.size();
            if (m >= 2 &&
                    (stack.get(m - 2) == 'A' && stack.get(m - 1) == 'B' ||
                            stack.get(m - 2) == 'C' && stack.get(m - 1) == 'D')) {
                stack.remove(m - 1);
                stack.remove(m - 2);
            }
        }
        return stack.size();
    }
}
