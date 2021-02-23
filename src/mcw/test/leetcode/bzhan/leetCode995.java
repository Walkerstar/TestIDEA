package mcw.test.leetcode.bzhan;

/**
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 *
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *
 * @author mcw 2021\2\18 0023-14:32
 */
public class leetCode995 {

    /**
     * 差分数组
     */
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }

            /* 由于模 2 意义下的加减法与异或等价，我们也可以用异或改写上面的代码。
            revCnt ^= diff[i];
            if (A[i] == revCnt) { // A[i] ^ revCnt == 0
                if (i + K > n) {
                    return -1;
                }
                revCnt ^= 1;
                diff[i + K] ^= 1;
            }*/
        }
        return ans;
    }

    /**
     * 滑动窗口
     */
    public int minKBitFlips1(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                // 复原数组元素，若允许修改数组 A，则可以省略
                A[i - K] -= 2;
            }
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt += 2;
                A[i] += 2;
            }
        }
        return ans;
    }

}
