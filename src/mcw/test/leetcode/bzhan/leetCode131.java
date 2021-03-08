package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * Test20
 * @author mcw 2021\3\7 0007-16:22
 */
public class leetCode131 {

    boolean[][] f;
    int[][] h;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    /**
     * 方法一：回溯 + 动态规划预处理
     */
    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return ret;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; j++) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 方法二：回溯 + 记忆化搜索
     */
    public List<List<String>> partition1(String s) {
        n = s.length();
        h = new int[n][n];
        dfs1(s, 0);
        return ret;
    }

    private void dfs1(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs1(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    private int isPalindrome(String s, int i, int j) {
        if (h[i][j] != 0) {
            return h[i][j];
        }
        if (i >= j) {
            h[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            h[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            h[i][j] = -1;
        }
        return h[i][j];
    }
}
