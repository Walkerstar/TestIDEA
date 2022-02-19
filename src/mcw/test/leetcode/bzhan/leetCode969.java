package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 *
 * 一次煎饼翻转的执行过程如下：
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 *
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 * @author MCW 2022/2/19
 */
public class leetCode969 {

    /**
     * 设一个元素的下标是 index，我们可以通过两次煎饼排序将它放到尾部：
     * 第一步选择 k=index+1，然后反转子数组 arr[0...k−1]，此时该元素已经被放到首部。
     * 第二步选择 k=n，其中 n 是数组 arr 的长度，然后反转整个数组，此时该元素已经被放到尾部。
     *
     * 通过以上两步操作，我们可以将当前数组的最大值放到尾部，然后将去掉尾部元素的数组作为新的处理对象，重复以上操作，直到处理对象的长度等于一，
     * 此时原数组已经完成排序，且需要的总操作数是 2×(n−1)，符合题目要求。如果最大值已经在尾部，我们可以省略对应的操作。
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList<>();
        for (int n = arr.length; n > 1; n--) {
            int index = 0;
            //找最大值
            for (int i = 1; i < n; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == n - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, n - 1);
            ret.add(index + 1);
            ret.add(n);
        }
        return ret;
    }

    public void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
