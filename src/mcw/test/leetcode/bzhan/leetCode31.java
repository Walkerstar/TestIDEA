package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * next permutation
 * 给定一组数的排列，找出下一个 比输入的数 大的 排列
 * @author mcw 2020\5\11 0011-15:26
 */
public class leetCode31 {

    /**
     * 方法1
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int replace = nums.length - 2;
        //从倒数第二个位置往前比较，如果当前数比他的后一位数要小，就结束循环
        while (replace >= 0) {
            if (nums[replace] < nums[replace + 1]) {
                break;
            }
            replace--;
        }
        //如果整个数以降序排列，则直接反转
        if (replace < 0) {
            Arrays.sort(nums);
            return;
        }
        //replace curr number with closest larger number
        int lgrIdx = replace + 1;
        while (lgrIdx < nums.length && nums[lgrIdx] > nums[replace]) {
            lgrIdx++;
        }
        int tmp = nums[replace];
        nums[replace] = nums[lgrIdx - 1];
        nums[lgrIdx - 1] = tmp;
        Arrays.sort(nums, replace + 1, nums.length);
    }


    /**
     * 方法2
     */
    public void nextPermutation2(int[] nums){
        int len = nums.length;
        //从右往左找到第一个减小的数字
        int swapIndex=len-1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i]<nums[i+1]) {
                swapIndex=i;
                break;
            }
        }
        //没找到说明本身就是最大的，直接反装
        if (swapIndex==len-1) {
            reverse(nums,0,len-1);
        }else {
            //找到了就与它左边更大的数交换，然后反转右边（升序变降序）
            int largerIndex=len-1;
            for (int i = len - 1; i > swapIndex; i--) {
                if (nums[i]>nums[swapIndex]) {
                    swap(nums,swapIndex,i);
                    break;
                }
            }
            reverse(nums,swapIndex+1,len-1);
        }
    }

    private void reverse(int[] nums, int start, int end) {
        while (start<end){
            swap(nums,start++,end--);
        }
    }

    private void swap(int[] nums, int m, int n) {
        int temp=nums[m];
        nums[m]=nums[n];
        nums[n]=temp;
    }
}
