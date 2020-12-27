package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * @author mcw 2020/12/20 19:32
 */
public class leetCode316 {

    public static String removeDuplicateLetters(String s){
        boolean[] v = new boolean[26];
        int[] num = new int[26];
        //记录每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //如果ch没有出现过，进入下面的循环
            if (!v[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    //如果num[sb.charAt(sb.length() - 1) - 'a'] > 0，则表明它还有多个，否则结束本次循环
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        v[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                v[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }
}
