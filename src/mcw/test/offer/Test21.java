package mcw.test.offer;

import java.util.Stack;

/**
 * @author mcw 2020\1\17 0017-14:25
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，判断第二个序列是否可能为该栈的弹出顺序。
 */
public class Test21 {

    public static boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        Stack<Integer> s = new Stack<>();
        //用于标识弹出序列的位置
        int popIndex = 0;

        for (int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);

            //如果栈不为空，且栈顶元素等于弹出序列
            while (!s.empty() && s.peek() == popA[popIndex]) {
                //出栈
                s.pop();
                //弹出序列向后一位
                popIndex++;
            }
        }
        return s.empty();
    }


    public static void main(String[] args) {
        boolean order = isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 1, 3, 2});
        System.out.println(order);
    }
}
