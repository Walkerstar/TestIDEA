package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * <p>
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * <p>
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 *
 * @author mcw 2022/1/27 11:53
 */
public class leetCode2047 {

    public static int countValidWords(String sentence) {
        int n = sentence.length();
        int l = 0, r = 0;
        int ret = 0;
        while (true) {
            while (l < n && sentence.charAt(1) == ' ') {
                l++;
            }
            if (l >= n) {
                break;
            }
            r = l + 1;
            while (r < n && sentence.charAt(r) != ' ') {
                r++;
            }
            if (isValid(sentence.substring(l, r))) {
                ret++;
            }
            l = r + 1;
        }
        return ret;
    }

    public static boolean isValid(String word) {
        int n = word.length();
        boolean hasHyphens = false;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(word.charAt(i))) {
                return false;
            } else if (word.charAt(i) == '-') {
                if (hasHyphens == true || i == 0 || i == n - 1 ||
                        !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
                    return false;
                }
                hasHyphens = true;
            } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                if (i != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int count(String sentence){
        //String parttern="(?:(?<=\\s)|(?<=^))([a-z]+\\-[a-z]+|[a-z]+)?(?(1)[\\!\\,\\.]?|[\\!\\,\\.])(?:(?=\\s)|(?=$))";
        int ans = 0;
        //有连字符
        String pattern1 = "[a-z]+[-]?[a-z]+[!|.|,]?";
        //没有连字符
        String pattern2 = "[a-z]*[!|.|,]?";
        String s[] = sentence.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > 0 && (s[i].matches(pattern1) || s[i].matches(pattern2))) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String sentence="!this  1-s b8d!";//"alice and  bob are playing stone-game10";
        System.out.println(countValidWords(sentence));
        System.out.println(count(sentence));
    }
}
