package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，请在无限整数序列[1，2，3，4，5，6...] 中找出并返回第 N 位上的数字
 * <p>
 * 例：
 * 输入: n=11    输出: 0   解释: [1,2,3,4,5,6,7,8,9,10,11] 第11位是 10 中的数字 0
 *
 * @author MCW 2021/11/30
 */
public class leetCode400 {

    /**
     * x 表示位数之和 （10~99 ,共有90个整数，每个整数位数为 2， 则位数和为 2 x 90 =180;
     *      类推可得 x ∈ [10^(x-1),10^x-1],即 x 位的位数之和是  x * 9 * 10^(x-1)）
     * d 表示整数的位数（例如：999，d=3）(由于 n 的最大值位 2^31-1,约为2*10^9,
     *      当 x =9 时，x * 9 * 10^(x-1) = 8.1 * 10^9 > 2^31-1，
     *      因此第 n 位数字所在的整数最多是9位数，令 d的上界为 9 即可)
     *
     * 在得到 d 的值之后，可以根据上述规律计算得到所有位数不超过 d−1 的整数的位数之和，然后得到第 n 位数在所有 d 位数的序列中的下标，
     * 为了方便计算，将下标转换成从 0 开始记数。具体而言，用 prevDigits 表示所有位数不超过 d−1 的整数的位数之和，
     * 则第 n 位数在所有 d 位数的序列中的下标是 index=n−prevDigits−1，index 的最小可能取值是 0。
     *
     * 得到下标 index 之后，可以得到无限整数序列中的第 n 位数字是第 └ index/d ┘ 个 d 位数的第 index mod d 位，
     * 注意编号都从 0 开始。
     *
     * 由于最小的 d 位数是 10^(d−1) ，因此第 n 位数字所在的整数是 10^(d-1)+ └ index/d ┘ ，
     * 该整数的右边第 d-(index mod d)-1 位（计数从 0 开始）即为无限整数序列中的第 n 位数字。
     */
    public int fielndNthDigit(int n) {
        int low = 1, high = 9;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (totalDigits(mid) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int d = low;
        int prevDigits = totalDigits(d - 1);
        int index = n - prevDigits - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }

    private int totalDigits(int length) {
        int digits = 0;
        int curLength = 1, curCount = 9;
        while (curLength <= length) {
            digits += curLength * curCount;
            curLength++;
            curCount *= 10;
        }
        return digits;
    }


    /**
     * 直接计算
     */
    public int findNthDigit2(int n) {
        int d = 1, count = 9;
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }
}
