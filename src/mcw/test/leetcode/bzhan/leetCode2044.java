package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 *
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 *
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 *
 * @author MCW 2022/3/15
 */
public class leetCode2044 {

    /**
     *记 n 是数组 nums 的长度，数组中的每个元素都可以选取或者不选取，因此数组的非空子集数目一共有 (2^n-1)个。
     * 可以用一个长度为 n 比特的整数来表示不同的子集，在整数的二进制表示中，n 个比特的值代表了对数组不同元素的取舍。
     * 第 i 位值为 1 则表示该子集选取对应元素，第 i 位值为 0 则表示该子集不选取对应元素。
     * 求出每个子集的按位或的值，并计算取到最大值时的子集个数。
     */
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0, cnt = 0;
        for (int i = 0; i < 1 << nums.length; i++) {
            int orVal = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    orVal |= nums[i];
                }
            }
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
        }
        return cnt;
    }

    int[] nums;
    int maxOr, cnt;

    public int countMaxOrSubsets2(int[] nums) {
        this.nums = nums;
        this.maxOr = 0;
        this.cnt = 0;
        dfs(0, 0);
        return cnt;
    }

    public void dfs(int pos, int orVal) {
        if (pos == nums.length) {
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
            return;
        }
        dfs(pos + 1, orVal | nums[pos]);
        dfs(pos + 1, orVal);
    }

}
