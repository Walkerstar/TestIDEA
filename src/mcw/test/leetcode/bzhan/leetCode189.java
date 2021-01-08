package mcw.test.leetcode.bzhan;

import mcw.test.jvm.WeakHashMapDemo;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 * @author mcw 2021/1/8 18:57
 */
public class leetCode189 {

    /**
     * 使用额外数组 时间复杂度 O(n),空间复杂度 O(n)
     */
    public void rotate(int[] nums, int k) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
    }

    /**
     * 环状替换 时间复杂度 O(n),空间复杂度 O(1)
     */
    public void rotate1(int[] nums,int k){
        int n = nums.length;
        k = k % n;
        //n 和 k 的最大公约数
        int count = gcd(k, n);
        for (int start = 0; start < count; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 数组翻转 时间复杂度 O(n),空间复杂度 O(1)
     */
    public void rotate2(int[] nums,int k){
        k%= nums.length;
        reverse(nums,0, nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start<end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }
}
