package mcw.test.leetcode.bzhan;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 * @author mcw 2020/12/18 20:43
 */
public class leetCode389 {
    
    public char findTheDifference(String s,String t){
        int[] ch=new int[26];
        for (int i = 0; i < s.length(); i++) {
                ch[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--ch[t.charAt(i)-'a']<0){
                return t.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * 异或
     */
    public char finTheDifference1(String s,String t){
        char res=0;
        for (char c : s.toCharArray()) {
            res^=c;
        }
        for (char c : t.toCharArray()) {
            res^=c;
        }
        return res;
    }

    public char finTheDifference2(String s,String t){
       return (char) (s+t).chars().reduce(0,(a, b)->a^b);
    }
}
