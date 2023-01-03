package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s ，由 n 个字符组成，每个字符不是 'X' 就是 'O' 。
 * <p>
 * 一次 操作 定义为从 s 中选出 三个连续字符 并将选中的每个字符都转换为 'O' 。注意，如果字符已经是 'O' ，只需要保持 不变 。
 * <p>
 * 返回将 s 中所有字符均转换为 'O' 需要执行的 最少 操作次数。
 * <p>
 * 提示：
 *
 * <li>3 <= s.length <= 1000</li>
 * <li>s[i] 为 'X' 或 'O'</li>
 *
 * @author mcw 2022/12/27 17:06
 */
public class leetCode2027 {

    /**
     * 方法一：模拟
     * <p>
     * 从左至右遍历 s，用 covered 表示 res 操作次数内最多可以转换的下标数。res 初始化为 0，covered 初始化为 −1。
     * 遍历时，如果当前元素为 ‘O’ 或当前下标已被覆盖了，则不需要额外操作数。仅当当前元素为 ‘X’ 且当前下标未被覆盖，需要增加一次操作，
     * 并更新 covered。最后返回操作数。
     */
    public static int minimumMoves(String s) {
        int covered = -1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X' && i > covered) {
                res++;
                //当前的 X 的 下标 + 2 ，就构成了 3 个连续的字符
                covered = i + 2;
            }
        }
        return res;
    }
}
