package mcw.test.leetcode.bzhan;

/**
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 * <p>
 * 请你返回 words 数组中 一致字符串 的数目。
 *
 * @author mcw 2022/11/8 15:48
 */
public class leetCode1684 {

    /**
     * 方法一：遍历
     * 由题意可知，字符串 allowed 和字符串数组 words 中的所有字符串都只包含小写字母，因此我们可以用一个 32 位整数来表示一个字符串出现的字母集合。
     * 如果一个字母出现了，那么将对应整数的位设为 1。使用 mask 表示字符串 allowed 出现的字母集合。
     * 依次遍历字符串数组 words，假设第 i 个字符串 words[i] 对应出现的字母集合为 mask_1 ，
     * 那么 words[i] 是一致字符串等价于 mask_1 是 mask 的子集，即 mask_1 ∪ mask = mask。
     * 统计一致字符串的数目并返回结果。
     */
    public static int countXConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (int i = 0; i < allowed.length(); i++) {
            char c = allowed.charAt(i);
            mask |= 1 << (c - 'a');
        }
        int res = 0;
        for (String word : words) {
            int mask1 = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mask1 |= 1 << (c - 'a');
            }
            if ((mask1 | mask) == mask) {
                res++;
            }
        }
        return res;
    }

    public static int countXConsistentStrings2(String allowed, String[] words) {
        int[] mask = new int[26];
        for (int i = 0; i < allowed.length(); i++) {
            char c = allowed.charAt(i);
            mask[(c - 'a')] = 1;
        }
        int res = 0;
        boolean flag=false;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (mask[c - 'a'] != 1) {
                    flag=false;
                    break;
                } else if (i == word.length() - 1) {
                    flag = true;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countXConsistentStrings2("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}));//2
        System.out.println(countXConsistentStrings2("abc", new String[]{"a","b","c","ab","ac","bc","abc"}));//7
        System.out.println(countXConsistentStrings2("cad", new String[]{"cc","acd","b","ba","bac","bad","ac","d"}));//4
    }
}
