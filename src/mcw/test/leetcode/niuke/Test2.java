package mcw.test.leetcode.niuke;

import java.util.Stack;

/**
 * @author mcw
 * @date 2019\11\27 0027-14:03
 *
 * 计算逆波兰表达式的值
 */
public class Test2 {
    public static int evalRPN(String[] tokens){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            try {
                stack.push(Integer.parseInt(tokens[i]));
            }catch (Exception e){
                int a=stack.pop();
                int b=stack.pop();
                if(tokens[i].equals("+")){
                    stack.push(a+b);
                }else if(tokens[i].equals("-")){
                    stack.push(b-a);
                }else if(tokens[i].equals("*")){
                    stack.push(a*b);
                }else if(tokens[i].equals("/")){
                    stack.push(b/a);
                }
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] str={"4","13","5","/","+"};
        System.out.println(evalRPN(str));
    }
}
