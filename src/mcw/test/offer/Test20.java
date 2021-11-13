package mcw.test.offer;


import java.util.Stack;

/**
 * @author mcw 2020\1\16 0016-14:59
 *
 * 定义栈的数据结构，请在该数据类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度为o(1)）
 */
public class Test20 {

     Stack<Integer> s1=new Stack<>();
     Stack<Integer> s2=new Stack<>(); //存储最小元素的栈
     Integer temp=null;

    public  void push(int node){
        s1.push(node);
        if(s2.isEmpty()){
            s2.push(node);
            temp=node;
        }else {
            temp=s2.peek();
            if(temp>node){
                s2.push(node);
            }else{
                s2.push(temp);
            }
        }
    }

    public void pop(){
        s1.pop();
        s2.pop();
    }

    public int top(){
        return s1.peek();
    }

    public int min(){
        return s2.peek();
    }
}
