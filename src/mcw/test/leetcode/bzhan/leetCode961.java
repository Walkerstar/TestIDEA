package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * <p>
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * @author MCW 2022/5/21
 */
public class leetCode961 {

    /**
     * 哈希表
     */
    public int repeatedNTimes(int[] nums) {
        Set<Integer> found = new HashSet<>();
        for (int num : nums) {
            if (!found.add(num)) {
                return num;
            }
        }
        //不可能的情况
        return -1;
    }

    /**
     * 方法二：数学
     * 我们可以考虑重复的元素 x 在数组 nums 中出现的位置。
     * <p>
     * 如果相邻的 x 之间至少都隔了 2 个位置，那么数组的总长度至少为：n + 2(n - 1) = 3n - 2
     * 当 n > 2 时，3n-2 > 2n，不存在满足要求的数组。因此一定存在两个相邻的 x，它们的位置是连续的，或者只隔了 1 个位置。
     * 当 n = 2 时，数组的长度最多为 2n = 4，因此最多只能隔 2 个位置。
     * <p>
     * 这样一来，我们只需要遍历所有间隔 2 个位置及以内的下标对，判断对应的元素是否相等即可。
     */
    public int repeatedNTimes2(int[] nums) {
        int n = nums.length;
        for (int gap = 1; gap <= 3; gap++) {
            for (int i = 0; i + gap < n; i++) {
                if (nums[i] == nums[i + gap]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * 方法三：随机选择
     * 我们可以每次随机选择两个不同的下标，判断它们对应的元素是否相等即可。如果相等，那么返回任意一个作为答案。
     */
    public int repeatedNTimes3(int[] nums) {
        int n = nums.length;
        Random random = new Random();
        while (true) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            if (x != y && nums[x] == nums[y]) {
                return nums[x];
            }
        }
    }


}