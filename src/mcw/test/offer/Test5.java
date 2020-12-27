package mcw.test.offer;

import java.util.Stack;

/**
 * @author mcw 2020\1\13 0013-14:24
 * 两个栈实现一个队列，完成pop和push操作。对列元素为int类型
 */
public class Test5 {
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();

    public void push(int node){
        stack1.push(node);
    }

    public int pop(){
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        int ans=stack2.pop();
        while(!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return ans;
    }
}
