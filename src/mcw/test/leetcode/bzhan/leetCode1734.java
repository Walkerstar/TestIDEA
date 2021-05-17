package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * <p>
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * <p>
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * @author mcw 2021\5\11 0011-10:58
 */
public class leetCode1734 {

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        //total 表示数组 perm 的全部元素的异或运算结果
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }

        //odd 表示数组 encoded 的所有下标为奇数的元素的异或运算结果
        int odd = 0;
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }
        int[] perm = new int[n];
        perm[0] = total ^ odd;
        for (int i = 0; i < n - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }
        return perm;
    }
}
