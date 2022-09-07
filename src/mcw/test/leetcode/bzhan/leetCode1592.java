package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
 * <p>
 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 * <p>
 * 返回 重新排列空格后的字符串 。
 *
 * @author mcw 2022/9/7 14:27
 */
public class leetCode1592 {

    public String reorderSpaces(String text) {
        int length = text.length();
        int spaceNum = 0;
        List<String> words = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (' ' == c) {
                spaceNum++;
            } else {
                s.append(c);
            }
            if (!s.toString().trim().equals("") || i == length - 1) {
                words.add(s.toString());
                s = new StringBuilder();
            }
        }

        s = new StringBuilder();
        if (words.size() == 1) {
            s.append(words.get(0));
            for (int i = 0; i < spaceNum; i++) {
                s.append("-");
            }
            return s.toString();
        }

        int b = spaceNum / (words.size() - 1);
        int c = spaceNum % (words.size() - 1);
        //System.out.println("b is " + b + ",  c is " + c + ",  words.size is " + words.size());

        for (int i = 0; i < words.size(); i++) {
            s.append(words.get(i));
            if (i < words.size() - 1) {
                for (int j = 0; j < b; j++) {
                    s.append("-");
                }
            }
        }
        for (int i = 0; i < c; i++) {
            s.append("-");
        }
        return s.toString();

        //LeetCode other people 的方法
        //String[] words = text.trim().split("\\s+");
        //int s = (int) text.chars().filter(x -> x == ' ').count(), m = words.length;
        //if (m <= 1) return String.join("", words).concat(" ".repeat(s));
        //return String.join(" ".repeat(s / (m - 1)), words)
        //        .concat(" ".repeat(s % (m - 1)));
    }
}
