package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2707. 字符串中的额外字符
 * <p>
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，
 * 每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 * <p>
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
 * 输出：1
 * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。
 * 只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
 * 输出：3
 * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。
 * 下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] 和 s 只包含小写英文字母。
 * dictionary 中的单词互不相同。
 *
 * @author MCW 2024/1/9
 */
public class leetCode2707 {


    /**
     * 动态规划
     */
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();

        // 定义 d[i] 为 s 前缀 s[0...i−1] 的子问题
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        var map = new HashMap<String, Integer>();
        for (String str : dictionary) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (map.containsKey(s.substring(j, i))) {
                    d[i] = Math.min(d[i], d[j]);
                }
            }
        }

        return d[n];
    }


    /**
     * 字典树优化
     */
    public int minExtraChar2(String s, String[] dictionary) {
        int n = s.length();

        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        Trie trie = new Trie();
        for (String str : dictionary) {
            var sb = new StringBuilder(str).reverse();
            trie.insert(sb.toString());
        }

        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            Trie node = trie;
            for (int j = i - 1; j >= 0; j--) {
                if (node != null) {
                    node = node.track(s.charAt(j));
                    if (node != null && node.isEnd) {
                        d[i] = Math.min(d[i], d[j]);
                    }
                }
            }
        }
        return d[n];
    }

    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
        }

        public Trie track(char ch) {
            Trie node = this;
            if (node == null || node.children[ch - 'a'] == null) {
                return null;
            }
            node = node.children[ch - 'a'];
            return node;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }
}
