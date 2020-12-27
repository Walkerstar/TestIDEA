package mcw.test.leetcode.bzhan;

import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\5\3 0003-14:50
 * generate parentheses(产生括号)
 * 给定一个数 n ，返回所有由 n 对括号组成的集合
 */
public class leetCode22 {

    public static List<String> generateParentheses(int n){
        LinkedList<String> res = new LinkedList<>();
        helper("",res,n,0,0);
        return res;
    }

    /**
     *
     * @param curr 当前字符串
     * @param res 结果集
     * @param n 括号数
     * @param left  已有的 "（"
     * @param right 已有的 ")"
     */
    private static void helper(String curr, List<String> res, int n, int left, int right) {
        if(right==n){
            res.add(curr);
            return;
        }
        //“（” 的数量 小于 n
        if(left<n){
            helper(curr+"(",res,n,left+1,right);
        }
        //")" 的数量 小于 “（” 的数量时
        if(right<left){
            helper(curr+")",res,n,left,right+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParentheses(3));
    }
}
