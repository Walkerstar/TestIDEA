package mcw.test.leetcode.bzhan;

/**
 * 当 A  的子数组  A[i], A[i+1], ..., A[j]  满足下列条件时，我们称其为湍流子数组：
 *<p></p>
 * 若  i <= k < j，当 k  为奇数时，  A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；<br>
 * 或 <br>
 * 若  i <= k < j，当 k 为偶数时，A[k] > A[k+1]  ，且当 k  为奇数时，  A[k] < A[k+1]。<br>
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。返回 A 的最大湍流子数组的长度。
 *
 * @author mcw 2021/2/08 15:21
 */
public class leetCode978 {

    /**
     * 动态规划
     */
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len < 2) return len;

        // 以 arr[i] 结尾，并且 arr[i - 1] < arr[i] 的湍流子数组的长度
        int[] increased = new int[len];
        // 以 arr[i] 结尾，并且 arr[i - 1] > arr[i] 的湍流子数组的长度
        int[] decreased = new int[len];

        increased[0] = 1;
        decreased[0] = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] < arr[i]) {
                increased[i] = decreased[i - 1] + 1;
                decreased[i] = 1;
            } else if (arr[i - 1] > arr[i]) {
                decreased[i] = increased[i - 1] + 1;
                increased[i] = 1;
            } else {
                increased[i] = 1;
                decreased[i] = 1;
            }
            res = Math.max(res, Math.max(increased[i], decreased[i]));
        }
        return res;
    }

    /**
     * 双指针
     */
    public int maxTurbulenceSize1(int[] arr) {
        int len = arr.length;
        if (len < 2) return len;

        int left = 0;
        int right = 1;
        // 为 true 表示 arr[i - 1] < arr[i]
        boolean pre = false;
        int res = 1;
        while (right < len) {
            boolean current = arr[right - 1] < arr[right];
            if (current == pre) {
                left = right - 1;
            }
            if (arr[right - 1] == arr[right]) {
                left = right;
            }
            right++;
            res = Math.max(res, right - left);
            pre = current;
        }
        return res;
    }
}
