package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Scramble String
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 *      1.如果字符串的长度为 1 ，算法停止
 *      2.如果字符串的长度 > 1 ，执行下述步骤：
 *          .在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 *          .随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 *          .在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 *
 * @author mcw 2020\7\1 0001-15:25
 */
public class leetCode87 {

    static Map<String, Map<String, Boolean>> map=new HashMap<>();

    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.length() == 1 && s1.equals(s2)) return true;
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        Arrays.sort(s1Char);
        Arrays.sort(s2Char);
        String str1 = new String(s1Char);
        String str2 = new String(s2Char);
        //如果排序后的 s1 和 s2 不相等 ，返回 false
        if (!str1.equals(str2)) {
            return false;
        }
        // 判断是否已经遍历过s1，然后判断是否有对应的s2
        if (map.containsKey(s1) && map.get(s1).containsKey(s2)){
            return map.get(s1).get(s2);
        }
        for (int len = 1; len < s1.length(); len++) {
            String s1Left = s1.substring(0, len);
            String s1Right = s1.substring(len, s1.length());
            //将 s1 的左边 len 个字符与 s2 的 左边 len 个字符互换，
            // 同时，将 s1 的右边 与 s2 的右边互换
            if ((isScramble(s1Left, s2.substring(0, len)) &&
                    (isScramble(s1Right, s2.substring(len, s2.length()))))
                    ||
                    //将 s1 的左边 len 个字符与 s2 的 右边剩余的 s2.length() - len 个字符互换，
                    // 同时，将 s1 的右边 与 s2 的左边互换
                    isScramble(s1Left, s2.substring(s2.length() - len, s2.length())) &&
                            (isScramble(s1Right, s2.substring(0, s2.length() - len)))) {
                // 对当前s1与s2进行一个存储
                map.computeIfAbsent(s1,z->new HashMap<>()).put(s2,true);
                return true;
            }
        }
        // 对当前s1与s2进行一个存储
        map.computeIfAbsent(s1,z->new HashMap<>()).put(s2,false);
        return false;
    }


    public boolean isScramble1(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        // 枚举区间长度 2～n
        for (int len = 2; len <= n; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
