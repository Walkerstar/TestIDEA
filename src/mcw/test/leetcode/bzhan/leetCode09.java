package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\1 0001-11:27
 * 判断一个数是否是回文的？
 */
public class leetCode09 {

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        int num = x;
        //找出该数的最高位数
        while (num / div >= 10) {
            div *= 10;
        }
        while (num != 0) {
            //当前数的最高位
            int left = num / div;
            //当前数的最低位
            int right = num % 10;
            if (left != right) {
                return false;
            }
            //将最高位和最低位去掉后的 新数
            num = (num - left * div) / 10;
            div /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(145787541));
    }
}

