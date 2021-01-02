package mcw.test.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author mcw 2020\1\28 0028-16:02
 * <p>
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值最大的值。
 * <p>
 * 例如：输入数组{2,3,4,2,6,2,5,1}  滑动窗口为 3  ，则最大值分别为{4,4,6,6,6,5}
 * <p>
 * 数组的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}   {2,[3,4,2],6,2,5,1}   {2,3,[4,2,6],2,5,1}
 * {2,3,4,[2,6,2],5,1}   {2,3,4,2,[6,2,5],1}   {2,3,4,2,6,[2,5,1]}
 */
public class Test64 {

    //循环
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();

        if (size > num.length || size == 0)
            return list;
        for (int i = 0; i <= num.length - size; i++) {
            int max = num[i];
            for (int j = i + 1; j < i + size; j++) {
                if (num[j] > max)
                    max = num[j];
            }
            list.add(max);
        }
        return list;
    }

    //双端队列，队列的第一个位置保存当前窗口的最大值，当窗口滑动一次
    //1.判断当前最大值是否过期 2.新增加的值从队尾开始比较，把所有的比他小的值丢掉
    public static ArrayList<Integer> maxInWindows1(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < num.length; i++) {
            begin = i + size - 1;
            if (q.isEmpty())
                q.add(i);
            else if (begin > q.peekFirst())
                q.pollFirst();

            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while ((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            // 添加当前值对应的数组下标
            q.add(i);
            if (begin >= 0)
                res.add(num[q.peekFirst()]);
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);

        for (Integer c : list) {
            System.out.print(c + "\t");
        }
    }
}
