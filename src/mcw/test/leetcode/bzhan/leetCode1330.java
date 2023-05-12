package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
 * <p>
 * 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
 * <p>
 * 请你找到可行的最大 数组值 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,5,4]
 * 输出：10
 * 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,9,24,2,1,10]
 * 输出：68
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3*10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * @author MCW 2023/5/12
 */
public class leetCode1330 {

    /**
     * 官方题解
     */
    public int maxValueAfterReverse(int[] nums) {
        int value = 0, n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            value += Math.abs(nums[i] - nums[i + 1]);
        }

        int mx1 = 0;
        for (int i = 1; i < n - 1; i++) {
            mx1 = Math.max(mx1, Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            mx1 = Math.max(mx1, Math.abs(nums[n - 1]) - Math.abs(nums[i] - nums[i - 1]));
        }
        int mx2 = Integer.MIN_VALUE, mn2 = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i], y = nums[i + 1];
            mx2 = Math.max(mx2, Math.min(x, y));
            mn2 = Math.min(mn2, Math.max(x, y));
        }
        return value + Math.max(mx1, 2 * (mx2 - mn2));
    }


    /**
     * @ 灵茶山艾府
     * 如果不翻转，或者翻转的是一个长为 1 的子数组，那么 nums 不变，此时的「数组值」记作 base。
     *
     * 示例 1 的 base=∣2−3∣+∣3−1∣+∣1−5∣+∣5−4∣=1+2+4+1=8。
     *
     * 为了计算出最大的「数组值」，考虑翻转后与翻转前的差值 d，那么答案为 base+d，所以 d 越大，答案也就越大。
     *
     * 假设从 nums[i] 到 nums[j] 的这段子数组翻转了，且 1 ≤ i < j < n−1（其中 n 为 nums 的长度）。
     * 设 a = nums[i−1],  b = nums[i],  x = nums[j],  y = nums[j+1]。
     *
     * 对于 i = 0 或 j = n−1 的翻转，单独用 O(n) 的时间枚举。
     *
     * 翻转前，这 4 个数对数组值的贡献为
     *  ∣a−b∣ + ∣x−y∣
     *
     * 翻转后，顺序变为  a,x,b,y，贡献为
     *  ∣a−x∣+∣b−y∣
     *
     * 得到
     *  d = ∣a−x∣ + ∣b−y∣ − ∣a−b∣ − ∣x−y∣  (1)
     *
     * 问题转换成求  d 的最大值。
     * <p>
     * 若干恒等式
     *
     * 对于 ∣a−b∣：
     * 如果 a ≥ b，结果是 a−b；
     * 如果 a < b，结果是 b−a；
     * 总而言之，结果就是大的减去小的。
     * 所以
     *  ∣a−b∣ = max(a,b) − min(a,b)       (2)
     *
     * 此外还有如下恒等式
     *  a + b = max(a,b) + min(a,b)       (3)
     *
     * (3)+(2) 得
     *  a + b + ∣a−b∣ = 2 * max(a,b)      (4)
     *
     * (3)−(2) 得
     *  a + b − ∣a−b∣ = 2 * min(a,b)      (5)
     *
     * 恒等式  (4) 和 (5) 是化简 (1) 式的钥匙。
     *
     * <p>
     *
     * 分类讨论
     *
     * a,b,x,y 这 4 个数的大小关系一共有 4!=24 种情况，例如 a ≤ b ≤ x ≤ y, b ≤ x ≤ a ≤ y 等等。
     *
     * 按照这 4 个数中的哪两个数是最小的两个，可以分为 C(4,2)=6 类，每类 4 种情况。
     * 利用对称性，只需讨论其中 3 类，便可以得到另外 3 类的结果。
     * <p>
     * 第 1 类： max(a,b) ≤ min(x,y)
     *
     * 把 a,b,x,y 画在数轴上，相当于 a 和 b 都在 x 和 y 的左边（或重合）。
     *
     * 那么
     * d = ∣a−x∣ + ∣b−y∣ − ∣a−b∣ − ∣x−y∣
     *   = (x−a) + (y−b) − ∣a−b∣ − ∣x−y∣
     *   = (x+y) − (a+b) − ∣a−b∣ − ∣x−y∣
     *   = (x+y − ∣x−y∣) − (a + b + ∣a−b∣) (带入上步 (4) ,(5) 式)
     *   = 2 * min(x,y) − 2 * max(a,b)
     *
     * 注意 max(a,b) ≤ min(x,y)，上式是 ≥ 0 的。
     *
     * 利用对称性，对于  max(x,y) ≤ min(a,b) 的  4 种情况，可以得到类似的结果
     *  d = 2 * min(a,b) − 2 * max(x,y) ≥ 0
     * 很好，已经讨论清楚  8 种情况了！
     *
     * <p>
     * 第 2 类：max(a,x) ≤ min(b,y)
     *
     * 把 a,b,x,y 画在数轴上，相当于  a 和  x 都在  b 和  y 的左边（或重合）。
     *
     * 那么
     * d = ∣a−x∣ + ∣b−y∣ − ∣a−b∣ − ∣x−y∣
     *   = ∣a−x∣ + ∣b−y∣ − (b−a) − (y−x)
     *   = ∣a−x∣ + ∣b−y∣ + (a+x) − (b+y)
     *   = (a + x + ∣a−x∣) − ( b + y − ∣b−y∣)
     *   = 2 * max(a,x) − 2 * min(b,y)
     *
     * 由于  max(a,x) ≤ min(b,y)，上式  ≤ 0。
     * 利用对称性，对于 max(b,y) ≤ min(a,x) 的  4 种情况，可以得到类似的结果。
     * d = 2 * max(b,y) − 2 * min(a,x) ≤ 0
     *
     * 所以这  8 种情况不会对  d 的最大值产生影响。（注意可以只翻转长为  1 的子数组，此时  d=0。）
     *
     * 很好，已经讨论清楚  16 种情况了！
     *
     * <p>
     * 第 3 类： max(a,y) ≤ min(b,x)
     * 把 a,b,x,y 画在数轴上，相当于  a 和  y 都在  b 和  x 的左边（或重合）。
     *
     * 那么
     * d = ∣a−x∣ + ∣b−y∣ − ∣a−b∣ − ∣x−y∣
     *   = (x−a) + (b−y) − (b−a) − (x−y)
     *   = 0
     * 利用对称性，对于  max(b,x) ≤ min(a,y) 的 4 种情况，同样可以得到  d=0。
     *
     * 所以这  8 种情况也不会对  d 的最大值产生影响。
     * 24 种情况讨论完毕。
     *
     * <p>
     * 算法
     * 由于只有第 1 类的情况会影响 d 的最大值，为了最大化 d，
     * 在遍历 nums 的所有相邻元素 a,b 的同时，维护 min(a,b) 的最大值 mx，以及 max(a,b) 的最小值 mn。
     *
     * 遍历结束后，如果 mx > mn，那么对应的 a,b,x,y 存在，且大小关系必然属于第 1 类讨论的 8 种情况之一。
     * 则有 d = 2 * (mx−mn)
     *
     * 如果  mx = mn，由于 d 初始值为  0，不会产生影响。
     *
     * 特别地，对于翻转范围在数组边界的情况（ i=0 或  j=n−1），单独枚举，并更新  d 的最大值。
     *
     */
    public int maxValueAfterReverse2(int[] nums) {
        //不翻转时，数组值
        int base = 0;

        //翻转前和翻转后的差值
        int d = 0;
        int n = nums.length;
        int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int a = nums[i - 1], b = nums[i];
            int dab = Math.abs(a - b);
            base += dab;
            mx = Math.max(mx, Math.min(a, b));
            mn = Math.min(mn, Math.max(a, b));
            d = Math.max(d, Math.max(Math.abs(nums[0] - b) - dab, // i=0
                    Math.abs(nums[n - 1] - a) - dab)); // j=n-1
        }
        return base + Math.max(d, 2 * (mx - mn));
    }


}
