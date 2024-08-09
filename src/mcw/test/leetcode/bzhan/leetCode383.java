package mcw.test.leetcode.bzhan;

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 示例 1：
 * <p>
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * <p>
 * 示例 2：
 * <p>
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote 和 magazine 由小写英文字母组成
 *
 * @author MCW 2024/1/7
 */
public class leetCode383 {

    /**
     * 方法一：字符统计
     * 题目要求使用字符串 magazine 中的字符来构建新的字符串 ransomNote，且 ransomNote 中的每个字符只能使用一次，
     * 只需要满足字符串 magazine 中的每个英文字母 (’a’-’z’) 的统计次数都大于等于 ransomNote 中相同字母的统计次数即可。
     * <p>
     * 如果字符串 magazine 的长度小于字符串 ransomNote 的长度，则我们可以肯定 magazine 无法构成 ransomNote，此时直接返回 false。
     * 首先统计 magazine 中每个英文字母 a 的次数 cnt[a]，再遍历统计 ransomNote 中每个英文字母的次数，
     * 如果发现 ransomNote 中存在某个英文字母 c 的统计次数大于 magazine 中该字母统计次数 cnt[c]，则此时我们直接返回 false。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
