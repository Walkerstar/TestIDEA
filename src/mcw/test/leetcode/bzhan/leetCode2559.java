package mcw.test.leetcode.bzhan;

/**
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 * <p>
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 * <p>
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 * <p>
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
 * 输出：[2,3,0]
 * 解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
 * 查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
 * 查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
 * 查询 [1,1] 结果为 0 。
 * 返回结果 [2,3,0] 。
 * <p>
 * 示例 2：
 * 输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
 * 输出：[3,2,1]
 * 解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 40
 * words[i] 仅由小写英文字母组成
 * sum(words[i].length) <= 3 * 10^5
 * 1 <= queries.length <= 10^5
 * 0 <= queries[j][0] <= queries[j][1] < words.length
 *
 * @author MCW 2023/6/2
 */
public class leetCode2559 {

    /**
     * 方法一：前缀和
     * 为方便表述，以下将以元音开头和结尾的字符串称为「元音字符串」。
     * <p>
     * 这道题要求返回一系列查询的答案，每个查询为计算特定区间中的元音字符串数。可以使用前缀和实现区间查询。
     * <p>
     * 用 n 表示数组 words 的长度，创建长度为 n+1 的数组 prefixSums，
     * 其中 prefixSums[i] 表示数组 words 的前 i 个字符串（即下标范围 [0,i−1] 的字符串）中的元音字符串数，prefixSums[0] = 0。
     * <p>
     * 从左到右遍历数组 words，对于下标 0 ≤ i < n，执行如下操作：
     * <p>
     * 如果 words[i] 是元音字符串，则 prefixSums[i+1] = prefixSums[i] + 1；
     * 如果 words[i] 不是元音字符串，则 prefixSums[i+1] = prefixSums[i]。
     * <p>
     * 得到前缀和数组之后，对于 0 ≤ i ≤ j < n，区间 [i,j] 中的元音字符串数是 prefixSums[j+1] − prefixSums[i]。
     * <p>
     * 用 ans[i] 表示第 i 个查询 queries[i] 的答案。如果 queries[i] = [ start_i , end_i ]，
     * 则 ans[i] = prefixSums[end_i + 1] − prefixSums[ start_i ]。
     * <p>
     * 遍历所有的查询之后，即可得到答案数组 ans。
     */
    public int[] vowelString(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int value = isVowelString(words[i]) ? 1 : 0;
            prefixSums[i + 1] = prefixSums[i] + value;
        }
        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = prefixSums[end + 1] - prefixSums[start];
        }
        return ans;
    }

    public boolean isVowelString(String word) {
        return isVowelLetter(word.charAt(0)) && isVowelLetter(word.charAt(word.length() - 1));
    }

    public boolean isVowelLetter(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
