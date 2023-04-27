package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * <p>
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * <p>
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * <p>
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 ["a","ba","bda","bdca"]
 * 示例 2:
 * <p>
 * 输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * 输出：5
 * 解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * 示例 3:
 * <p>
 * 输入：words = ["abcd","dbqca"]
 * 输出：1
 * 解释：字链["abcd"]是最长的字链之一。
 * ["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] 仅由小写英文字母组成。
 *
 * @author mcw 2023/4/27 11:58
 */
public class leetCode1048 {

    /**
     * 方法一：动态规划
     * 思路与算法
     * <p>
     * 根据题意可知，对于字符串「前身」的定义为：
     * <p>
     * 不改变其他字符的顺序 ，在 wordA 的任何地方添加恰好一个字母使其变成 wordB，那么我们认为 wordA 是 wordB 的前身。
     * 将 wordB 中去掉任意一个字母，其余字符保持不变构成的字符串即为 wordB 的前身。
     * 因此对于每个字符串 s，假设其所有的前身 s' 为结尾的最长链的长度为 l，即可知道以 s 为结尾的最长链的长度为 l + 1。
     * 为保证我们求 s 的最长链时，其所有的前身的最长链的长度均已求出，需要将所有的字符串按照长度大小进行排序。
     * 假设字符串 s 最长链的长度为 cnt(s) 的前身为 s'_0,s'_1,s'_2, ... ,s'_k ，则此时可以知道
     * <p>
     * cnt(s)=max(cnt(s'_i )), i ∈ [0,k]
     * <p>
     * 根据以上结论，实际计算过程如下：
     * <p>
     * 1.首先对字符串数组  words 按照字符串长度的大小进行排序；
     * 2.依次遍历每个字符串  words[i]，并初始以  words[i] 为结尾的最长链的长度 cnt[words[i]] 为 1；
     * 3.依次尝试去掉  words[i] 中的每个字符，并构成其可能的前身 prev，在哈希表 cnt 查找 prev 对应的最长链长度，如果 cnt+1 大于 cnt[words[i]]，则更新 cnt[words[i]]；
     * 4.最终返回可能的最长链的长度即可。
     */
    public int LongestStrChain(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        for (String word : words) {
            cnt.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                if (cnt.containsKey(prev)) {
                    cnt.put(word, Math.max(cnt.get(word), cnt.get(prev) + 1));
                }
            }
            res = Math.max(res, cnt.get(word));
        }
        return res;
    }
}
