package mcw.test.leetcode.niuke;


import java.util.HashSet;
import java.util.Set;

/**
 * @author mcw 2020\2\19 0019-11:19
 * <p>
 * 给定一个字符串 S 和一组单词 Dict,判断 S 是否可以用空格分割成一个单词序列，
 * 使得单词序列的所有单词都是Dict中的单词
 */
public class Test12 {

    /**
     * 状态转移方程
     * f(i) 表示s[0,i]是否可以分词
     * f(i)=f(j) && f(j+1,i);  0<= j < i
     */
    public boolean wordBreak(String s, Set<String> dict) {
        int len = s.length();
        boolean[] arrays = new boolean[len + 1];
        arrays[0] = true;
        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arrays[j] && dict.contains(s.substring(j, i))) {
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }


    public static void main(String[] args) {
        Test12 object=new Test12();
        Set<String> dict=new HashSet<>();
        String[] s={"leet","code"};
        for (String str : s) {
            dict.add(str);
        }
        boolean wordBreak = object.wordBreak("leetcode", dict);
        System.out.println(wordBreak);
    }
}
