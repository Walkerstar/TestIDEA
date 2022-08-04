package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * <p>
 * 实现 WordFilter 类：
 * <p>
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 *
 * @author mcw 2022/7/14 16:25
 */
public class leetCode745 {

    /**
     * 方法一：计算每个单词的前缀后缀组合可能性
     * <p>
     * 预先计算出每个单词的前缀后缀组合可能性，用特殊符号连接，作为键，对应的最大下标作为值保存入哈希表。
     * 检索时，同样用特殊符号连接前后缀，在哈希表中进行搜索。
     */
    public class WordFilter {
        Map<String, Integer> dictionary;

        public WordFilter(String[] words) {
            dictionary = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int m = word.length();
                for (int prefixLength = 1; prefixLength <= m; prefixLength++) {
                    for (int suffixLength = 1; suffixLength <= m; suffixLength++) {
                        dictionary.put(word.substring(0, prefixLength) + "#" + word.substring(m - suffixLength), i);
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            return dictionary.getOrDefault(pref + "#" + suff, -1);
        }
    }

    /**
     * 方法二：字典树
     * 思路
     * <p>
     * 调用 f 时，如果前缀和后缀的长度相同，那么此题可以用字典树来解决。
     * 初始化时，只需将单词正序和倒序后得到的单词对依次插入字典树即可。比如要插入 “apple" 时，
     * 只需依次插入 (`a', `e'), (`p', `l'), (`p', `p'), (`l', `p'), (`e', `a') 即可。
     * 这样初始化后，对于前缀和后缀相同的检索，也只需要在字典树上检索前缀和后缀倒序得到的单词对。
     * <p>
     * 但是调用 f 时，还有可能遇到前缀和后缀长度不同的情况。为了应对这一情况，可以将短的字符串用特殊字符补足，使得前缀和后缀长度相同。
     * 而在初始化时，也需要考虑到这个情况，特殊字符组成的单词对，也要插入字典树中。
     */
    public static class wordFilterWithTrie {
        Trie trie;
        String weightKey;

        public wordFilterWithTrie(String[] words) {
            trie = new Trie();
            weightKey = "##";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                Trie cur = trie;
                int m = word.length();
                for (int j = 0; j < m; j++) {
                    Trie temp = cur;

                    //当前后缀位数不一致时，前缀尾部补上 # ，后缀头部补上 #
                    //计算当前单词的所有前缀
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append(word.charAt(k)).append("#").toString();
                        if (!temp.children.containsKey(key)) {
                            temp.children.put(key, new Trie());
                        }
                        temp = temp.children.get(key);
                        temp.weight.put(weightKey, i);
                    }

                    //计算当前单词的所有后缀
                    temp = cur;
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append("#").append(word.charAt(m - k - 1)).toString();
                        if (!temp.children.containsKey(key)) {
                            temp.children.put(key, new Trie());
                        }
                        temp = temp.children.get(key);
                        temp.weight.put(weightKey, i);
                    }

                    //当前后缀的位数一致时，取 前后各一位作为 key .例如：prefix=1234 suffix=7890 ,则 key = {10,29,38,47}
                    String key = new StringBuilder().append(word.charAt(j)).append(word.charAt(m - j - 1)).toString();
                    if (!cur.children.containsKey(key)) {
                        cur.children.put(key, new Trie());
                    }
                    cur = cur.children.get(key);
                    cur.weight.put(weightKey, i);
                }
            }
        }

        public int f(String pref, String suff) {
            Trie cur = trie;
            int m = Math.max(pref.length(), suff.length());
            for (int i = 0; i < m; i++) {
                char c1 = i < pref.length() ? pref.charAt(i) : '#';
                char c2 = i < suff.length() ? suff.charAt(suff.length() - 1 - i) : '#';
                String key = new StringBuilder().append(c1).append(c2).toString();
                if (!cur.children.containsKey(key)) {
                    return -1;
                }
                cur = cur.children.get(key);
            }
            return cur.weight.get(weightKey);
        }
    }

    public static class Trie {
        Map<String, Trie> children;
        Map<String, Integer> weight;

        public Trie() {
            children = new HashMap<>();
            weight = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        String[] words = {"ppllo","apple", "axxx", "ccce"};
        WordFilter w1= new leetCode745().new WordFilter(words);
        System.out.println(w1.f("a", "e"));

        wordFilterWithTrie w2=new wordFilterWithTrie(words);
        System.out.println(w2.f("a", "e"));
    }
}
