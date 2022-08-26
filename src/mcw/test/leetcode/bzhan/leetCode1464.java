package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给你一个整数数组nums，请你选择两个不同的下标 i 和 j ，使 (nums[i]-1)(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 *
 * @author MCW 2022/8/26
 */
public class leetCode1464 {

    public int maxProduct(int[] nums) {
        int first = Math.max(nums[0], nums[1]);
        int second = Math.min(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > first) {
                //先更改第二大的值
                second = first;
                first = nums[i];
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return (first - 1) * (second - 1);
    }


    public int maxProduct2(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    public int maxProduct3(int[] nums) {
        //构建最大堆
        Queue<Integer> heap = new PriorityQueue<>((i, j) -> j - i);
        for (int num : nums) {
            heap.offer(num);
        }
        //取最大元素和第二大的元素
        int f1 = heap.poll();
        int f2 = heap.poll();

        return (f1 - 1) * (f2 - 1);
    }


}
