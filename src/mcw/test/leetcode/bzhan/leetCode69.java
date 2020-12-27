package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\5 0005-14:44
 * Sqrt(x)
 */
public class leetCode69 {
    /**
     * 二分法
     */
    public static int mySqrt(int n){
        if (n <= 0) return 0;
        int start = 1, end = (int) Math.sqrt(Integer.MAX_VALUE);
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid * mid == n) return mid;
            if (mid * mid > n) end = mid;
            else start = mid;
        }
        if (end * end <= n) return end;
        else return start;
    }

    public static int sqrt(int n) {
        //当前的平方数
        int curr = 0;
        //记录第几个数
        int res = 0;
        //差值
        int add = 1;
        while (curr <= n) {
            if (Integer.MAX_VALUE - curr < add) return res;
            curr += add;
            res++;
            add += 2;
        }
        return res - 1;
    }
}
