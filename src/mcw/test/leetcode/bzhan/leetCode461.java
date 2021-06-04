package mcw.test.leetcode.bzhan;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 2^31.
 *
 * @author mcw 2021/5/27 17:09
 */
public class leetCode461 {

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance1(int x, int y) {
        int s = x ^ y;
        int ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    public int hammingDistance2(int x, int y) {
        int s = x ^ y;
        int ret = 0;
        while (s != 0) {
            s &= s - 1;
            ret++;
        }
        return ret;
    }
}
