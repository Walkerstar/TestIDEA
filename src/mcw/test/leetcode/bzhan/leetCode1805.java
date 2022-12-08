package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * <p>
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * <p>
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * <p>
 * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
 *
 * @author mcw 2022/12/6 15:01
 */
public class leetCode1805 {


    public int numDifferentIntegers(String word) {
        int length = word.length();
        Set<String> set = new HashSet<>();
        int p1 = 0, p2 = 0;
        while (true) {
            //找出数字开头
            while (p1 < length && !Character.isDigit(word.charAt(p1))) {
                p1++;
            }
            if (p1 == length) {
                break;
            }
            p2 = p1;
            //找出数字结尾
            while (p2 < length && Character.isDigit(word.charAt(p2))) {
                p2++;
            }
            //去除数字前缀 0
            while (p2 - p1 > 1 && word.charAt(p1) == '0') {
                p1++;
            }
            set.add(word.substring(p1, p2));
            p1 = p2;
        }
        return set.size();
    }

}
