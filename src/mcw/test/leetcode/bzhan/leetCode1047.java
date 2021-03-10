package mcw.test.leetcode.bzhan;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * @author mcw 2021\3\9 0009-17:26
 */
public class leetCode1047 {

    public static String removeDuplicates(String S) {
        StringBuilder stack = new StringBuilder();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char ch = stack.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }




    public static String removeDuplicates1(String S) {
        Deque<Character> stack=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        char[] chars = S.toCharArray();
        stack.addLast(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char c = S.charAt(i);
            if (!stack.isEmpty() && stack.peekLast().equals(c)){
                stack.pollLast();
            }else {
                stack.addLast(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return new String(sb);
    }



}
