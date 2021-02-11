package mcw.test.leetcode.bzhan;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，
 * 找到包含重复字母的最长子串的长度。注意：字符串长度 和 k 不会超过 10^4。
 *
 * @author mcw 2021/2/2 17:58
 */
public class leetCode424 {

    /**
     * 1.右边界先移动找到一个满足题意的可以替换 k 个字符以后，所有字符都变成一样的当前看来最长的子串，直到右边界纳入一个字符以后，不能满足的时候停下；<br>
     * 2.然后考虑左边界向右移动，左边界只须要向右移动一格以后，右边界就又可以开始向右移动了，继续尝试找到更长的目标子串；<br>
     * 3.替换后的最长重复子串就产生在右边界、左边界交替向右移动的过程中。
     */
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int max = 0;
        int left = 0, right = 0;
        while (right < n) {
            //计算当前(left,right)区间中每个字符出现的次数
            num[s.charAt(right) - 'A']++;

            //记录当前(left，right)区间的字符最大出现次数
            max = Math.max(max, num[s.charAt(right) - 'A']);

            //如果当前区间的长度比 字符出现的最大次数 max + 替换次数 k 大，则当前区间不符合题意
            //让 left 指针指向的字符的次数减 1 ，然后 left 指针右移 1 位
            if (right - left + 1 > max + k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
