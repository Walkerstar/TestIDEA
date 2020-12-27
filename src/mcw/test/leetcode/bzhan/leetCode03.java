package mcw.test.leetcode.bzhan;

/**
 * 求一个字符串中 最长的不重复的子串的长度
 * 例： “acdedx” 返回 5 ，最长子串为 "abcde"
 * 思路：
 * 创建两个指针 left，right；以及一个 boolean 类型的数组；当 right 指针右移时，依次记录该字符是否被访问，
 * 如果 未访问则标记为 true， 如果已访问，就记录当前子串的最大值，循环找出与 right 指针指向字符的相同字符的位置，
 * 然后，left，right 同时 移动一位，进行下个字符串 的查找，遍历结束后，与最后一个字符串的长度进行比较
 * @author mcw 2020\4\30 0030-14:47
 */
public class leetCode03 {

    public static int lengthSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0;
        int n = s.length();
        boolean[] used = new boolean[128];
        int max = 0;
        while (right < n) {
            //如果未被访问，标记访问，right右移
            if (!used[s.charAt(right)]) {
                used[s.charAt(right)] = true;
            } else {
                //已被访问，记录最大值，left右移
                max = Math.max(max, right - left);
                while (left < right && s.charAt(right) != s.charAt(left)) {
                    used[s.charAt(left)] = false;
                    left++;
                }
                left++;
            }
            right++;
        }
        max = Math.max(max, right - left);
        return max;
    }

    public static void main(String[] args) {
        String s="adsdasdbc";
        System.out.println(lengthSubstring(s));
    }
}
