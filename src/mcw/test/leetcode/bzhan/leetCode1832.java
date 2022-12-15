package mcw.test.leetcode.bzhan;

/**
 * 全字母句 指包含英语字母表中每个字母至少一次的句子。
 * <p>
 * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
 * <p>
 * 如果是，返回 true ；否则，返回 false 。
 *
 * @author mcw 2022/12/13 10:50
 */
public class leetCode1832 {

    /**
     * 因为 sentence 仅由小写英文字母组成，所以我们用一个长度为 26 的数组exist 来记录每种字符是否出现即可。
     * <p>
     * 具体的，我们遍历 sentence 中的每个字符 c，如果 c 是字母表中的第 i (0≤i<26) 个字母，就将 exist[i] 置为 true。
     * 最后检查 exist 中是否存在 false，如果存在返回 false，否则返回 true。
     */
    public static boolean checkIfPangram(String sentence) {
        boolean[] exist = new boolean[26];
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            exist[c - 'a'] = true;
        }
        for (boolean b : exist) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用数组记录每种字符是否出现仍然需要 O(C) 的空间复杂度。由于字符集仅有 26 个，我们可以使用一个长度为 26 的二进制数字来表示字符集合，
     * 这个二进制数字使用 32 位带符号整型变量即可。
     * <p>
     * 二进制数字的第 i 位对应字符集中的第 i 个字符，例如 0 对应 a，1 对应 b，23 对应 x 等。
     * <p>
     * 初始化整型变量 exist 为 0，遍历 sentence 中的每个字符 c，如果 c 是字母表中的第 i (0≤i<26) 个字母，就将 exist 的二进制表示中第 i 位赋值为 1。
     * 在实现过程中，将 exist 与 2^i 做或运算，2^i 可以用左移运算实现。
     * <p>
     * 最后，我们需要判断 exist 是否等于 2^26 - 1，这个数字的第 0∼25 位都为 1，其余位为 0。如果等于，返回 true，否则返回 false。
     */
    public static boolean checkIfPangram2(String sentence) {
        int state = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            state |= 1 << (c - 'a');
        }
        return state == (1 << 26) - 1;
    }

}
