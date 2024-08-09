package mcw.test.leetcode.bzhan;

/**
 * 1702. 修改后的最大二进制字符串
 * <p>
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
 * <p>
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
 * 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：binary = "000110"
 * 输出："111011"
 * 解释：一个可行的转换为：
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 * <p>
 * 示例 2：
 * <p>
 * 输入：binary = "01"
 * 输出："01"
 * 解释："01" 没办法进行任何转换。
 * <p>
 * 提示：
 * <p>
 * 1 <= binary.length <= 10^5
 * binary 仅包含 '0' 和 '1' 。
 *
 * @author MCW 2024/4/10
 */
public class leetCode1702 {

    /**
     * 方法一：模拟 + 贪心
     * 思路与算法
     * <p>
     * 我们从字符串左边第一位开始依次遍历，如果是 1 则不用改变，如果是 0，我们则想办法将其变成 1。
     * <p>
     * 我们会找到下一位出现的 0，利用操作 2 我们可以使得这两个 0 相邻，再使用操作 1 使得 00 变成 10。
     * <p>
     * 我们依次执行这个操作，直到字符串中没有第二个 0，或者达到字符串结尾。
     */
    public String maximumBinaryString(String binary) {
        int n = binary.length();
        char[] s = binary.toCharArray();
        int j = 0;

        for (int i = 0; i < n; ++i) {
            if (s[i] == '0') {
                while (j <= i || (j < n && s[j] == '1')) {
                    j++;
                }
                if (j < n) {
                    s[j] = '1';
                    s[i] = '1';
                    s[i + 1] = '0';
                }
            }
        }
        return new String(s);
    }


    /**
     * 方法二：直接构造
     * 思路与算法
     * <p>
     * 我们注意到最终结果，至多有一个 0。如果输入字符串中没有 0，则直接返回结果。如果有 0，
     * 结果中 0 的位置取决于字符串第一个 0 的位置，之后每多一个 0 便可以向后移动一位。
     * <p>
     * 所以我们只需要求出字符串中第一个 0 的下标，以及 0 的出现的个数，即可直接构造结果。
     */
    public String maximumBinaryString2(String binary) {
        int n = binary.length(), i = binary.indexOf('0');
        if (i < 0) {
            return binary;
        }

        int zeros = 0;
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n; j++) {
            if (binary.charAt(j) == '0') {
                zeros++;
            }
            s.append('1');
        }
        s.setCharAt(i + zeros - 1, '0');
        return s.toString();
    }
}
