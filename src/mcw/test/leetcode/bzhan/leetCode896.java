package mcw.test.leetcode.bzhan;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。   当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * @author mcw 2021\2\28 0028-19:15
 */
public class leetCode896 {

    /**
     * 遍历数组 A，若既遇到了 A[i] > A[i+1]又遇到了 A[i'] < A[i'+1],则说明 A 既不是单调递增的，也不是单调递减的。
     */
    public static boolean isMonotonic(int[] A) {
        boolean inc=true;
        boolean dec=true;

        for (int i = 0; i < A.length-1; i++) {
                if (A[i] < A[i + 1]) {
                    dec= false;
                }
                if (A[i] > A[i + 1]) {
                    inc= false;
                }
        }
        return inc || dec;
    }


    /**
     * 遍历两次数组，分别判断其是否为单调递增或单调递减
     */
    public boolean isMonotonic1(int[] a) {
        return isSorted(a, true) || isSorted(a, false);
    }

    private boolean isSorted(int[] a, boolean flag) {
        if (flag) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] < a[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
