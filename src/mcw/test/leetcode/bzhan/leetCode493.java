package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * @author mcw 2020\11\28 0028-18:58
 */
public class leetCode493 {

    /**
     * 方法一：归并排序
     *  1.初始化两个指针i，j分别指向A1，A2的头部
     *  2.如果A1[i] > 2*A2[j],那么A1[i]及A1[i]后面的所有元素都符合要求，更新答案并后移j
     *  3.否则，后移i
     *  4.接下来我们需要合并A1，A2以备解决后面更大的子问题使用
     *  5.返回我们的答案
     */
    public int findReversedPairs(int[] nums, int left, int right) {
        int res = 0;
        int mid = left + (right - left) / 2;
        int i = left;
        int j = mid + 1;
        for (; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                res += mid - i + 1;
                j++;
            }
        }
        return res;
    }

    public int mergeSort(int[] nums, int[] numsSorted, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int res = mergeSort(nums, numsSorted, left, mid) +
                mergeSort(nums, numsSorted, mid + 1, right) +
                findReversedPairs(nums, left, right);
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                numsSorted[k++] = nums[i++];
            } else {
                numsSorted[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            numsSorted[k++] = nums[i++];
        }
        while (j <= right) {
            numsSorted[k++] = nums[j++];
        }
        for (int d = left; d <= right; d++) {
            nums[d] = numsSorted[d];
        }
        return res;
    }

    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] numsSorted = new int[nums.length];
        return mergeSort(nums, numsSorted, 0, nums.length - 1);
    }

    /**
     * 方法二：树状数组
     * 树状数组支持的基本查询为求出 [1,val] 之间的整数数量。因此，对于 nums[i] 而言，我们首先查询 [1,2*nums[i]] 的数量，
     * 再求出 [1,maxValue] 的数量（其中 maxValue 为数组中最大元素的二倍）。二者相减，就能够得到以 i 为右端点的翻转对数量。
     *
     * 由于数组中整数的范围可能很大，故在使用树状数组解法之前，需要利用哈希表将所有可能出现的整数，映射到连续的整数区间内。
     */
    public int revresePairs(int[] nums) {
        Set<Long> allNumbers = new TreeSet<>();
        for (int x : nums) {
            allNumbers.add((long) x);
            allNumbers.add((long) 2 * x);
        }
        //利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<>();
        int idx = 0;
        for (Long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < nums.length; i++) {
            int left = values.get((long) nums[i] * 2);
            int right = values.size() - 1;
            ret += bit.query(right + 1) - bit.query(left + 1);
            bit.update(values.get((long) nums[i]) + 1, 1);
        }
        return ret;
    }

}

class BIT {
    int[] tree;
    int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
}
