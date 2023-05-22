package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
 * <p>
 * 注意：本题中，每个活字字模只能使用一次。
 * <p>
 * 示例 1：
 * 输入："AAB"
 * 输出：8
 * 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
 * <p>
 * 示例 2：
 * 输入："AAABBC"
 * 输出：188
 * <p>
 * 示例 3：
 * 输入："V"
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= tiles.length <= 7
 * tiles 由大写英文字母组成
 *
 * @author MCW 2023/5/19
 */
public class leetCode1079 {

    /**
     * 方法一：回溯
     * 思路与算法
     * <p>
     * 题目要求返回非空字母序列的数目。我们首先统计所有字符的个数，然后用回溯来查找所有排列。
     * <p>
     * 每次搜索中，我们依次遍历所有剩余的字符，每次遍历选用当前字符，将当前字符减一，递归调用搜索函数，累计搜索完成后的结果，再把当前字符数量加一进行「回溯」。
     * <p>
     * 递归循环的结束条件是第  n 次递归，此时我们用完了所有字符，找到一个可行字母序列，返回结果为 1。
     * <p>
     * 最后我们返回搜索到的所有字符串，因为题目要求返回非空字符串的数目，所以结果还要减一。
     */
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : tiles.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        Set<Character> tile = new HashSet<>(count.keySet());
        return dfs(tiles.length(), count, tile) - 1;
    }

    public int dfs(int i, Map<Character, Integer> count, Set<Character> tile) {
        if (i == 0) {
            return 1;
        }
        int res = 1;
        for (char t : tile) {
            if (count.get(t) > 0) {
                count.put(t, count.get(t) - 1);
                res += dfs(i - 1, count, tile);
                count.put(t, count.get(t) + 1);
            }
        }
        return res;
    }
}
