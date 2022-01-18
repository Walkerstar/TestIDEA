package mcw.test.leetcode.bzhan;

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 示例 1：
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * @author mcw 2022/1/10 11:29
 */
public class leetCode306 {

    public static boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int secondStart  = 0; secondStart  < n - 1; secondStart ++) {
            //排除 0 开头的情况
            if (num.charAt(0) == '0' && secondStart  != 1) {
                break;
            }
            for (int secondEnd  = secondStart ; secondEnd  < n - 1; secondEnd ++) {
                //排除 0 开头的情况
                if (num.charAt(secondStart ) == '0' && secondStart  != secondEnd ) {
                    break;
                }
                if (valid(secondStart , secondEnd , num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean valid(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStar = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStar, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStar;
            secondEnd = thirdEnd;
        }
        return false;
    }

    /**
     * 计算第 3 个数 ;
     * 序列最新确定的两个数中，位于前面的数字为 first
     * 序列最新确定的两个数中，位于后面的数字为 second
     * @param firstStart first 的最高位在 num 中的下标为 firstStart
     * @param firstEnd first 的最低位在 num 中的下标为 firstEnd
     * @param secondStart second 的最高位在 num 中的下标为 secondStart
     * @param secondEnd second 的最低位在 num 中的下标为 secondEnd
     */
    private static String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuffer third = new StringBuffer();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            //进位数
            carry = cur / 10;
            //当前位数
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
    }
}
