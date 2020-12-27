package mcw.test.leetcode.bzhan;

/**
 * 上升下降字符串
 * @author mcw 2020\11\25 0025-16:13
 */
public class leetCode1370 {

    public String sortString(String s){
        //记录每个字母出现的次数
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        /*
        可以直接创建一个大小为 26 的桶，记录每种字符的数量。每次提取最长的上升或下降字符串时，
        我们直接顺序或逆序遍历这个桶。
         */
        StringBuilder ret = new StringBuilder();
        while (ret.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return ret.toString();
    }
}