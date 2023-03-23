package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 等差数列 。更正式地，数列 s 是等差数列，只需要满足：对于每个有效的 i ， s[i+1] - s[i] == s[1] - s[0] 都成立。
 * <p>
 * 例如，下面这些都是 等差数列 ：
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 下面的数列 不是等差数列 ：
 * <p>
 * 1, 1, 2, 5, 7
 * 给你一个由 n 个整数组成的数组 nums，和两个由 m 个整数组成的数组 l 和 r，后两个数组表示 m 组范围查询，其中第 i 个查询对应范围 [l[i], r[i]] 。所有数组的下标都是 从 0 开始 的。
 * <p>
 * 返回 boolean 元素构成的答案列表 answer 。如果子数组 nums[l[i]], nums[l[i]+1], ... , nums[r[i]] 可以 重新排列 形成 等差数列 ，answer[i] 的值就是 true；否则answer[i] 的值就是 false 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * 输出：[true,false,true]
 * 解释：
 * 第 0 个查询，对应子数组 [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。
 * 第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。
 * 第 2 个查询，对应子数组 [5,9,3,7] 。可以重新排列为等差数列 [3,5,7,9] 。
 * 示例 2：
 * <p>
 * 输入：nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
 * 输出：[false,true,false,false,true,true]
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * m == l.length
 * m == r.length
 * 2 <= n <= 500
 * 1 <= m <= 500
 * 0 <= l[i] < r[i] < n
 * -10^5 <= nums[i] <= 10^5
 *
 * @author mcw 2023/3/23 10:55
 */
public class leetCode1630 {

    /**
     * 我们首先进行一次遍历，找到子数组中的最小值 a 和最大值 b。显然，a 和 b 就是等差数列的首项和末项，
     * 并且 d=(b−a)/(l-1)  就是等差数列的公差。
     * 如果 b-a 不能被 l-1 整除，那么我们直接知道子数组不可能重新排列成等差数列。
     * <p>
     * 如果 a = b（即 d=0），那么子数组中的每个元素都应该相同，说明它是一个等差数列。
     * 否则，我们已经知道了等差数列的首项、公差和长度，这个等差数列唯一确定。
     * <p>
     * 我们再进行一次遍历，对于遍历到的元素 x，我们可以通过：t = (x-a) / d
     * <p>
     * 获取它是等差数列中的第 (0≤t<l) 项。如果 x-a 不能被 d 整除，那么我们同样知道子数组不可能重新排列成等差数列，可以退出遍历。
     * 否则，当我们遍历完整个子数组后，第 0,1,2,⋯,l−1 项应该均出现了一次，这里我们可以使用哈希表或者一个长度为 l 的数组进行判断。
     * <p>
     * 这样一来，我们使用两次遍历以及一个长度为 l 的辅助数组，就可以判断子数组是否可以重新排列成等差数列，时间复杂度为 O(l)。
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left = l[i], right = r[i];
            //找出查询数组内的最大最小值
            int minv = nums[left], maxv = nums[right];
            for (int j = left + 1; j <= right; j++) {
                minv = Math.min(minv, nums[j]);
                maxv = Math.max(maxv, nums[j]);
            }
            //如果最大最小值相等，则说明查询数组内的元素都一样，是等差数列
            if (minv == maxv) {
                ans.add(true);
                continue;
            }
            //计算公差，如果余数不为 0，这说明此查询数组不是等差数列
            if ((maxv - minv) % (right - left) != 0) {
                ans.add(false);
                continue;
            }
            //公差
            int d = (maxv - minv) / (right - left);
            boolean flag = true;
            //记录元素是否访问
            boolean[] seen = new boolean[right - left + 1];
            for (int j = left; j <= right; j++) {
                //依次计算查询数组内每项元素对公差取余，如果不为 0，则说明不是等差数列
                if ((nums[j] - minv) % d != 0) {
                    flag = false;
                    break;
                }
                //如果该元素已被访问过，则说明该查询数组不是等差数列
                int t = (nums[j] - minv) / d;
                if (seen[t]) {
                    flag = false;
                    break;
                }
                seen[t] = true;
            }
            ans.add(flag);
        }
        return ans;
    }
}
