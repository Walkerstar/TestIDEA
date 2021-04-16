package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
 * 这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 提示：
 *
 *       nums1.length == m + n
 *       nums2.length == n
 *       0 <= m, n <= 200
 *       1 <= m + n <= 200
 *       -109 <= nums1[i], nums2[i] <= 109
 *
 * 来源：力扣（Leed所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mcw 2021\4\05 0005-15:04
 */
public class leetCode88 {

    /**
     * 方法一：直接合并后排序
     */
    public void merge(int[] nums1,int m,int[] nums2,int n){
        for (int i = 0; i !=n; i++) {
            nums1[m+i]=nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 方法二：双指针
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; i++) {
            nums1[i] = sorted[i];
        }
    }


    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
