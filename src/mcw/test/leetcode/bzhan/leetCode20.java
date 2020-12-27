package mcw.test.leetcode.bzhan;

import java.util.Stack;

/**
 * @author mcw 2020\5\2 0002-17:12
 * 括弧校验
 *检查输入的括弧是否配对，是，输出 true, 否，输出 false
 */
public class leetCode20 {

    public static boolean isValid(String s) {
        Stack<Character> mark = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                mark.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (mark.isEmpty()) {
                    return false;
                }
                char cur = mark.pop();
                if (cur == '(' && s.charAt(i) != ')') return false;
                if (cur == '[' && s.charAt(i) != ']') return false;
                if (cur == '{' && s.charAt(i) != '}') return false;
            }
        }
        return mark.isEmpty();
    }

}
