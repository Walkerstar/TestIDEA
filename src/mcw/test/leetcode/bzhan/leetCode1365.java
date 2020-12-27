package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
 *  2 <= nums.length <= 500  ;   0 <= nums[i] <= 100
 *
 * 换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]。
 * 以数组形式返回答案。
 *
 * @author mcw 2020/10/26 11:21
 */
public class leetCode1365 {

    /**
     * 计数排序
     * 注意到数组元素的值域为 [0,100]，所以可以考虑建立一个频次数组 cnt,cnt[i] 表示数字 i 出现的次数。
     * 那么对于数字 i 而言，小于它的数目就为 cnt[0...i−1] 的总和。
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt = new int[101];
        int n = nums.length;
        for (int i : nums) {
            cnt[i]++;
        }
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }

    /**
     * 快速排序
     * 我们也可以将数组排序，并记录每一个数在原数组中的位置。对于排序后的数组中的每一个数，我们找出其左侧第一个小于它的数，
     * 这样就能够知道数组中小于该数的数量。
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        //Arrays.sort(data, (o1, o2) -> o1[0]-o2[0]);
        Arrays.sort(data, Comparator.comparingInt(o -> o[0]));
        int[] arr = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            arr[data[i][1]] = prev;
        }
        return arr;
    }

    /**
     * 暴力
     * 对于数组中的每一个元素，我们都遍历数组一次，统计小于当前元素的数的数目。
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int num : nums) {
                if (num < nums[i]) {
                    sum++;
                }
            }
            arr[i] = sum;
        }
        return arr;
    }
}
