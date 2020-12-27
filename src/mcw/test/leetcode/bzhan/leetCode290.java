package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * @author mcw 2020/12/16 19:25
 */
public class leetCode290 {
    public boolean wordPatten(String pattern, String str) {
        Map<String, Character> strToChar = new HashMap<>();
        Map<Character, String> charToStr = new HashMap<>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); p++) {
            char ch = pattern.charAt(p);
            if (i > m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (strToChar.containsKey(tmp) && strToChar.get(tmp) != ch) {
                return false;
            }
            if (charToStr.containsKey(ch) && !tmp.equals(charToStr.get(ch))) {
                return false;
            }
            strToChar.put(tmp, ch);
            charToStr.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }


    public boolean wordPattern1(String pattern, String s) {
        String[] strs = s.split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        Map map = new HashMap();
        for (int i = 0; i < pattern.length(); i++) {
            //利用 map.put() 方法的返回值，（key 第一次 put的时候返回 null,第 n 次put 则返回第 n-1 次的 value）
            if (map.put(pattern.charAt(i), i) != map.put(strs[i], i)) {
                return false;
            }
        }
        return true;
    }

}
