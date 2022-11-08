package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。
 * 单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * <p>
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 *
 * @author mcw 2022/11/3 17:16
 */
public class leetCode1668 {

    /**
     * 简单枚举 + 动态规划
     */
    public static int maxRepeating(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        if (n < m) {
            return 0;
        }
        int[] f = new int[n];
        for (int i = m - 1; i < n; i++) {
            boolean valid = true;
            for (int j = 0; j < m; j++) {
                if (sequence.charAt(i - m + j + 1) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                f[i] = (i == m - 1 ? 0 : f[i - m]) + 1;
            }
        }
        return Arrays.stream(f).max().getAsInt();
    }

    /**
     * KMP
     */
    public static int maxRepeating2(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        if (n < m) {
            return 0;
        }

        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; i++) {
            int j = fail[i - 1];
            while (j != -1 && word.charAt(j + 1) != word.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == word.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int[] f = new int[n];
        int j = -1;
        for (int i = 0; i < n; i++) {
            while (j != -1 && word.charAt(j + 1) != sequence.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == sequence.charAt(i)) {
                ++j;
                if (j == m - 1) {
                    f[i] = (i > m ? f[i - m] : 0) + 1;
                    j = fail[j];
                }
            }
        }
        return Arrays.stream(f).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(maxRepeating2("ababababdfgab", "ab"));
    }
}
