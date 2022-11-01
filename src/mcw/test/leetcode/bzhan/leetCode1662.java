package mcw.test.leetcode.bzhan;

/**
 * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
 *
 * <b>数组表示的字符串</b> 是由数组中的所有元素 按顺序 连接形成的字符串。
 *
 * @author mcw 2022/11/1 16:16
 */
public class leetCode1662 {


    public boolean arrayStringAreEqual(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    public String join(String[] words) {
        StringBuilder ret = new StringBuilder();
        for (String word : words) {
            ret.append(word);
        }
        return ret.toString();
    }

    public boolean arrayStringAreEqual2(String[] word1, String[] word2) {
        int p1 = 0, p2 = 0, i = 0, j = 0;
        while (p1 < word1.length && p2 < word2.length) {
            if (word1[p1].charAt(i) != word2[p2].charAt(j)) {
                return false;
            }
            i++;
            if (i == word1[p1].length()) {
                p1++;
                i = 0;
            }
            j++;
            if (j == word2[p2].length()) {
                p2++;
                j = 0;
            }
        }
        return p1 == word1.length && p2 == word2.length;
    }


}
