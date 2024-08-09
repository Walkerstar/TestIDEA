package mcw.test.leetcode.bzhan;

import java.util.HashMap;

/**
 * 2085. 统计出现过一次的公共字符串
 * <p>
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * 输出：2
 * 解释：
 * - "leetcode" 在两个数组中都恰好出现一次，计入答案。
 * - "amazing" 在两个数组中都恰好出现一次，计入答案。
 * - "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
 * - "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
 * 所以，有 2 个字符串在两个数组中都恰好出现了一次。
 * <p>
 * 示例 2：
 * <p>
 * 输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * 输出：0
 * 解释：没有字符串在两个数组中都恰好出现一次。
 * <p>
 * 示例 3：
 * <p>
 * 输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * 输出：1
 * 解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words1.length, words2.length <= 1000
 * 1 <= words1[i].length, words2[j].length <= 30
 * words1[i] 和 words2[j] 都只包含小写英文字母。
 *
 * @author MCW 2024/1/12
 */
public class leetCode2085 {

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 我们用两个哈希表分别统计 word1 与 word2 中字符串的出现次数。
     * <p>
     * 随后，我们遍历第一个哈希表中的字符串，检查它在 word1 与 word2 中的出现次数是否均为 1。
     * 与此同时，我们统计出现过一次的公共字符串个数，如果某个字符串在两个数组中均只出现一次，那么我们将个数加 1。
     * 最终，我们返回该个数作为答案。
     */
    public int counterWords(String[] words1, String[] words2) {
        // 统计字符串出现频率
        var freq1 = new HashMap<String, Integer>();
        var freq2 = new HashMap<String, Integer>();

        for (String s : words1) {
            freq1.put(s, freq1.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            freq2.put(s, freq2.getOrDefault(s, 0) + 1);
        }

        // 遍历 words1 出现的字符串并判断是否满足要求
        int res = 0;
        for (String s : freq1.keySet()) {
            if (freq1.get(s) == 1 && freq2.getOrDefault(s, 0) == 1) {
                res++;
            }
        }
        return res;
    }
}
