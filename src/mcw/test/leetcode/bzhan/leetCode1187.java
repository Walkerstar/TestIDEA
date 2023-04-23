package mcw.test.leetcode.bzhan;

import mcw.test.search.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
 * <p>
 * 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，
 * 然后进行赋值运算 arr1[i] = arr2[j]。
 * <p>
 * 如果无法让 arr1 严格递增，请返回 -1。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * 输出：1
 * 解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。
 * 示例 2：
 * <p>
 * 输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * 输出：2
 * 解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。
 * <p>
 * 示例3：
 * <p>
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * 输出：-1
 * 解释：无法使 arr1 严格递增。
 * <p>
 * 提示：
 * <p>
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 *
 * @author mcw 2023/4/20 10:56
 */
public class leetCode1187 {

    public static final int INF = 0x3f3f3f3f;

    /**
     * 方法一：动态规划
     * 思路与算法
     * <p>
     * 此题为经典的「300. 最长递增子序列」问题的变形题目，我们可以参考类似的题目解法。
     * 首先我们思考一下，由于要求数组严格递增，因此数组中不可能存在相同的元素，对于数组 arr_2来说，
     * 可以不需要考虑数组中的重复元素，可以预处理去除 arr_2的重复元素，
     * 假设数组 arr_1 的长度为 n，数组 arr_2 的长度为 m，此时可以知道最多可以替换的次数为 min(n,m)。
     * 如何才能定义动态规划的递推公式，这就需要进行思考。
     * <p>
     * 我们设 dp[i][j] 表示数组 arr_1 中的前 i 个元素进行了 j 次替换后组成严格递增子数组末尾元素的最小值。
     * 当我们遍历 arr_1的第 i 个元素时，此时 arr_1 [i] 要么进行替换，要么进行保留，实际可以分类进行讨论:
     * <p>
     * 1. 此时如果 arr_1[i] 需要进行保留，则 arr_1 [i] 一定严格大于前 i-1 个元素替换后组成的严格递增子数组最末尾的元素。
     * 假设前 i-1 个元素经过了 j 次变换后得到的递增子数组的末尾元素的最小值为 dp[i−1][j]，
     * 如果满足 arr_1 [i] > dp[i−1][j]，则此时 arr_1[i] 可以保留加入到该子数组中且构成的数组严格递增；<br>
     * <p>
     * 2. 此时如果 arr_1[i] 需要进行替换，则替换后的元素一定严格大于前 i-1 个元素替换后组成的严格递增子数组最末尾的元素。
     * 假设前 i-1 个元素经过了 j-1 次变换后得到的递增子数组的末尾元素的最小值为 dp[i−1][j−1]，
     * 此时我们从 arr_2 找到严格大于 dp[i−1][j−1] 的最小元素 arr_2[k]，则此时将 arr_2[k] 加入到该子数组中且构成数组严格递增；<br>
     * <p>
     * 3. 综上可知，每个元素在替换时只有两种选择，要么选择保留当前元素 arr_1，要么从 arr_2 中选择一个满足条件的最小元素加入到数组中，
     * 最少替换方案一定包含在上述替换方法中。我们可以得到以下递推关系：
     * <p>
     * { dp[i][j]=min(dp[i][j],arr_1[i],  if arr_1[i]>dp[i−1][j] <br>
     * { dp[i][j]=min(dp[i][j],arr_2[k]), if arr_2[k]>dp[i−1][j−1]
     * <p>
     * 为了便于计算，我们将 dp[i][j] 的初始值都设为 ∞，为了便于计算在最开始加一个哨兵，此时令 dp[0][0]=−1 表示最小值。实际计算过程如下:
     * <p>
     * 1. 为了方便计算，需要对 arr2 进行预处理，去掉其中的重复元素，为了快速找到数组 arr2,还需要对 arr2进行排序；
     * <p>
     * 2. 依次尝试计算前 i 个元素在满足 j 次替换时的最小元素：
     * <p>
     * 2.1 如果当前元素 arr [i] 大于 dp[i][j−1]，此时可以尝试将 arr[i] 替换为 dp[i][j]，即此时 dp[i][j]=min(dp[i][j],arr[i])。<br>
     * 2.2 如果前 i-1 个元素可以满足 j-1 次替换后成为严格递增数组，即满足 dp[i−1][j−1] ≠ ∞，可以尝试在第 j 次替换掉 arr[i]，<br>
     * 2.3 此时根据贪心原则，利用二分查找可以快速的找到严格大于 dp[i−1][j−1] 的最小值进行替换即可。<br>
     * <p>
     * 3. 设当前数组 arr[i] 的长度为 n，如果前 n 个元素满足 j 次替换后成为严格递增数组，此时我们找到最小的 j 返回即可。
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        //对 arr2 进行排序并去重
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int i : arr2) {
            if (i != prev) {
                list.add(i);
                prev = i;
            }
        }

        //定义动态规划 数组，
        int n = arr1.length;
        int m = list.size();
        // dp[i][j] 表示数组 arr1 中的前 i 个元素进行了 j 次替换后组成严格递增子数组末尾元素的最小值
        int[][] dp = new int[n + 1][Math.min(m, n) + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, m); j++) {
                //如果当前元素大于序列的最后一个元素
                if (arr1[i - 1] > dp[i - 1][j]) {
                    dp[i][j] = arr1[i - 1];
                }
                if (j > 0 && dp[i - 1][j - 1] != INF) {
                    //查找严格大于dp[i-1][j-1] 的最小元素
                    int idx = binarySearch(list, j - 1, dp[i - 1][j - 1]);
                    if (idx != list.size()) {
                        dp[i][j] = Math.min(dp[i][j], list.get(idx));
                    }
                }
                if (i == n && dp[i][j] != INF) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int binarySearch(List<Integer> list, int low, int target) {
        int high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 动态规划二
     */
    public int makeArrayIncreasing2(int[] arr1, int[] arr2) {
        //对 arr2 进行排序并去重
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int i : arr2) {
            if (i != prev) {
                list.add(i);
                prev = i;
            }
        }

        int[] temp = new int[arr1.length + 2];
        System.arraycopy(arr1, 0, temp, 1, arr1.length);
        //右侧哨兵
        temp[arr1.length + 1] = INF;
        //左侧哨兵
        temp[0] = -1;
        arr1 = temp;
        int n = arr1.length;
        int m = list.size();

        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            //arr[i] 左侧的元素不进行替换
            if (arr1[i - 1] < arr1[i]) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
            //替换ar1[i]左边的连续 j个元素
            for (int j = 1; j < i; j++) {
                //arr2的最优替换的起点大于arr1[ij-1] 的最小元素
                int k = binarySearch(list, arr1[i - j - 1]);
                //arr2的最优替换的终点必须满足小于arr1[i]
                if (k + j - 1 < m && list.get(k + j - 1) < arr1[i]) {
                    dp[i] = Math.min(dp[i], dp[i - j - 1] + j);
                }
            }
        }
        return dp[n - 1] == INF ? -1 : dp[n - 1];
    }

    public int binarySearch(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
