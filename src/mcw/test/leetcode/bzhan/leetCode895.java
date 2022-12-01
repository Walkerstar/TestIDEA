package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * <p>
 * 实现 FreqStack 类:
 * <p>
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * @author mcw 2022/11/30 16:16
 */
public class leetCode895 {

    class FreqStack {
        /**
         * 记录每个元素出现的次数，即 key= 元素， value= 元素频率
         */
        private Map<Integer, Integer> freq;

        /**
         * 记录每个频率对应的栈序列 ，即 key= 频率 ，value= 栈序列
         */
        private Map<Integer, Deque<Integer>> group;

        /**
         * 当前最大频率
         */
        private int maxFreq;

        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<>();
            maxFreq = 0;
        }

        public void push(int val) {
            //记录 存入值的 频率
            freq.put(val, freq.getOrDefault(val, 0) + 1);

            //如果 val 的频率的没有对应的栈序列，则创建
            group.putIfAbsent(freq.get(val), new ArrayDeque<>());

            //取出相应的栈序列，推入 val
            group.get(freq.get(val)).push(val);

            //记录此时的最大值
            maxFreq = Math.max(maxFreq, freq.get(val));
        }

        public int pop() {
            //取出当前最大频率的栈的队首值
            int val = group.get(maxFreq).peek();

            //将该值出现的频率减一
            freq.put(val, freq.get(val) - 1);

            //将对应栈的该值弹出栈
            group.get(maxFreq).pop();

            //如果当前频率对应的栈为空，则将当前频率减一
            if (group.get(maxFreq).isEmpty()) {
                maxFreq--;
            }
            return val;
        }
    }
}
