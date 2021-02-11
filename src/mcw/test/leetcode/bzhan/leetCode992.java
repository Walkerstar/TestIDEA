package mcw.test.leetcode.bzhan;

/**
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
 *
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 *
 * 返回 A 中好子数组的数目。
 *
 * @author mcw 2021/2/09 15:12
 */
public class leetCode992 {

    public int subArrayWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0;
        int right = 0;
        //[left,right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left,right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }



    public int subarraysWithKDistinct1(int[] A, int K) {
        int n = A.length;
        int[] num1 = new int[n + 1];
        int[] num2 = new int[n + 1];
        int tot1 = 0, tot2 = 0;
        int left1 = 0, left2 = 0, right = 0;
        int ret = 0;
        while (right < n) {
            if (num1[A[right]] == 0) {
                tot1++;
            }
            num1[A[right]]++;
            if (num2[A[right]] == 0) {
                tot2++;
            }
            num2[A[right]]++;
            while (tot1 > K) {
                num1[A[left1]]--;
                if (num1[A[left1]] == 0) {
                    tot1--;
                }
                left1++;
            }
            while (tot2 > K - 1) {
                num2[A[left2]]--;
                if (num2[A[left2]] == 0) {
                    tot2--;
                }
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }
}
