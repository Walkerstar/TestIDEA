package mcw.test.leetcode.bzhan;

/**
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *
 * @author mcw 2022/4/7 14:08
 */
public class leetCode796 {

    /**
     * 首先，如果 s 和 goal 的长度不一样，那么无论怎么旋转，s 都不能得到 goal，返回 false。在长度一样（都为 n）的前提下，
     * 假设 s 旋转 i 位，则与 goal 中的某一位字符 goal[j] 对应的原 s 中的字符应该为 s[(i+j) mod n]。在固定 i 的情况下，
     * 遍历所有 j，若对应字符都相同，则返回 true。否则，继续遍历其他候选的 i。若所有的 i 都不能使 s 变成 goal，则返回 false。
     */
    public boolean rotateString(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 首先，如果 s 和 goal 的长度不一样，那么无论怎么旋转，s 都不能得到 goal，返回 false。字符串 s + s 包含了所有 s
     * 可以通过旋转操作得到的字符串，只需要检查 goal 是否为 s + s 的子字符串即可。
     * KMP 算法
     */
    public boolean rotateString2(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * KMP 算法
     */
    public boolean rotateString3(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        char[] c1 = s.toCharArray();
        char[] c2 = goal.toCharArray();
        int[] next = new int[c2.length];
        //next 计算模板
        for (int i = 1; i < c2.length; i++) {
            int j = next[i - 1];
            while (j > 0 && c2[i] != c2[j]) {
                j = next[j - 1];
            }
            if (c2[i] == c2[j]) {
                next[i] = j + 1;
            }
        }
        //KMP 匹配模板
        for (int i = 0, j = 0; i < 2 * c1.length; i++, j++) {
            char a = c1[i % c1.length];
            if (a != c2[j]) {
                while (j > 0 && c2[j] != a) {
                    j = next[j - 1];
                }
                if (j == 0 && a != c2[0]) {
                    j = -1;
                }
            } else if (j == c1.length - 1) {
                //c2 已经匹配至末端，可返回 true
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new leetCode796().rotateString3("abcde", "abced"));
    }
}
