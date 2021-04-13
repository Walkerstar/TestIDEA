package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * @author mcw 2021\4\12 0012-15:11
 */
public class leetCode179 {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        //转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });
        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    public String largestNumber1(int[] nums) {
        //自定义排序 A+B < B+A
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for (int x : nums) {
            heap.offer(String.valueOf(x));
        }
        StringBuilder res = new StringBuilder();
        while (heap.size() > 0) {
            res.append(heap.poll());
        }
        if (res.charAt(0) == '0') return "0";
        return res.toString();
    }
}
