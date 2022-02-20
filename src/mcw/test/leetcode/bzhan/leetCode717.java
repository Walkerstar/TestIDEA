package mcw.test.leetcode.bzhan;

/**
 * 有两种特殊字符：
 *
 * 第一种字符可以用一个比特 0 来表示
 * 第二种字符可以用两个比特(10 或 11)来表示、
 * 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
 *
 * @author MCW 2022/2/20
 */
public class leetCode717 {

    /**
     * 方法一：正序遍历
     * 根据题意，第一种字符一定以 0 开头，第二种字符一定以 1 开头。
     *
     * 我们可以对 bits 数组从左到右遍历。当遍历到 bits[i] 时，如果 bits[i]=0，说明遇到了第一种字符，将 i 的值增加 1；
     * 如果 bits[i]=1，说明遇到了第二种字符，可以跳过 bits[i+1]（注意题目保证 bits 一定以 00 结尾，所以 bits[i] 一定不是末尾比特，
     * 因此 bits[i+1] 必然存在），将 i 的值增加 2。
     *
     * 上述流程也说明 bits 的编码方式是唯一确定的，因此若遍历到 i=n−1，那么说明最后一个字符一定是第一种字符。
     */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }

    /**
     * 方法二：倒序遍历
     * 根据题意，0 一定是一个字符的结尾。
     *
     * 我们可以找到 bits 的倒数第二个 0 的位置，记作 i（不存在时定义为 −1），那么 bits[i+1] 一定是一个字符的开头，
     * 且从 bits[i+1] 到 bits[n−2] 的这 n−2−i 个比特均为 1。
     *
     * 如果 n−2−i 为偶数，则这些比特 1 组成了 (n−2−i)/2 个第二种字符，所以 bits 的最后一个比特 0 一定组成了第一种字符。
     * 如果 n−2−i 为奇数，则这些比特 1 的前 n−3−i 个比特组成了 (n−3−i)/2 个第二种字符，多出的一个比特 1 和 bits 的最后一个比特 0 组成第二种字符。
     *
     * 由于 n−i 和 n−2−i 的奇偶性相同，我们可以通过判断 n−i 是否为偶数来判断最后一个字符是否为第一种字符，
     * 若为偶数则返回 true，否则返回 false。
     */
    public boolean isOneBitCharacter2(int[] bits) {
        int n = bits.length;
        int i = n - 2;
        //倒数第二个 0 的位置
        while (i >= 0 && bits[i] == 1) {
            --i;
        }
        return (n - i) % 2 == 0;
    }
}
