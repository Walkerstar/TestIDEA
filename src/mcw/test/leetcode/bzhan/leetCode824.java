package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * <p>
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * <p>
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 *
 * @author mcw 2022/4/21 15:40
 */
public class leetCode824 {

    public String toGoatLatin(String sentence) {
        Set<Character> vowels = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }};

        int n = sentence.length();
        int i = 0, cnt = 1;
        StringBuffer ans = new StringBuffer();

        while (i < n) {
            int j = i;
            //统计当前单词长度
            while (j < n && sentence.charAt(j) != ' ') {
                ++j;
            }
            //记录 单词索引 （ + 1 个 'a'）
            ++cnt;
            if (cnt != 2) {
                ans.append(' ');
            }
            if (vowels.contains(sentence.charAt(i))) {
                ans.append(sentence.substring(i, j));
            } else {
                ans.append(sentence.substring(i + 1, j));
                ans.append(sentence.charAt(i));
            }
            ans.append('m');
            for (int k = 0; k < cnt; ++k) {
                ans.append('a');
            }

            //跳过空格，到下一个单词头部
            i = j + 1;
        }

        return ans.toString();
    }

    public String toGoatLatin2(String sentence) {
        String[] s = sentence.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            String word = s[i].toUpperCase();
            if (word.startsWith("A") ||
                    word.startsWith("E") ||
                    word.startsWith("I") ||
                    word.startsWith("O") ||
                    word.startsWith("U")) {
                sb.append(s[i]).append("ma");
            } else {
                sb.append(s[i].substring(1)).append(s[i].charAt(0)).append("ma");
            }

            for (int j = 0; j < i + 1; j++) {
                sb.append("a");
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
