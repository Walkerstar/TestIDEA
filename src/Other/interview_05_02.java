package Other;

/**
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * <p>
 * 示例1:
 * <p>
 * 输入：0.625
 * 输出："0.101"
 * 示例2:
 * <p>
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * <p>
 * 提示：
 * <p>
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 *
 * @author mcw 2023/3/2 11:26
 */
public class interview_05_02 {

    /**
     * 将实数的十进制表示转换成二进制表示的方法是：每次将实数乘以 2，将此时的整数部分添加到二进制表示的末尾，然后将整数部分置为 0，
     * 重复上述操作，直到小数部分变成 0 或者小数部分出现循环时结束操作。
     * 当小数部分变成 0 时，得到二进制表示下的有限小数；
     * 当小数部分出现循环时，得到二进制表示下的无限循环小数。
     * <p>
     * 由于这道题要求二进制表示的长度最多为 32 位，否则返回 “ERROR"，因此不需要判断给定实数的二进制表示的结果是有限小数还是无限循环小数，
     * 而是在小数部分变成 0 或者二进制表示的长度超过 32 位时结束操作。当操作结束时，如果二进制表示的长度不超过 32 位则返回二进制表示，
     * 否则返回 “ERROR"。
     */
    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        while (sb.length() <= 32 && num != 0) {
            num *= 2;
            int digit = (int) num;
            sb.append(digit);
            num -= digit;
        }
        return sb.length() <= 32 ? sb.toString() : "ERROR";
    }

    /**
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/bianry-number-to-string-lcci/solutions/2141577/zheng-ming-zhi-duo-xun-huan-6-ci-pythonj-b6sj/
     */
    public String printBin2(double num) {
        StringBuilder bin = new StringBuilder("0.");
        for (int i = 0; i < 6; ++i) { // 至多循环 6 次
            num *= 2;
            if (num < 1)
                bin.append('0');
            else {
                bin.append('1');
                if (--num == 0)
                    return bin.toString();
            }
        }
        return "ERROR";
    }

}
