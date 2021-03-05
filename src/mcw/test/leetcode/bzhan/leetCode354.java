package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封.
 *
 * @author mcw 2021\3\4 0004-22:38
 */
public class leetCode354 {

    /**
     * 动态规划
     */
    public int maxEnvelops(int[][] envelops) {
        if (envelops.length == 0) {
            return 0;
        }

        int n = envelops.length;
        Arrays.sort(envelops, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelops[j][1] < envelops[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 基于二分查找的动态规划
     */
    public int maxEnvelops1(int[][] envelops) {
        if (envelops.length == 0) {
            return 0;
        }

        int n = envelops.length;
        Arrays.sort(envelops, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        List<Integer> f = new ArrayList<>();
        f.add(envelops[0][1]);
        for (int i = 1; i < n; i++) {
            int num = envelops[i][1];
            if (num > f.size() - 1) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index,num);
            }
        }
        return f.size();
    }

    private int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * 最长递增子序列
     */
    public int maxEnvelops2(int[][] envelops) {
        int n = envelops.length;
        int[] f = new int[n];
        Arrays.sort(envelops, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e2[1] - e1[1];
            } else {
                return e1[0] - e2[0];
            }
        });

        int res = 0;
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelops[j][1] < envelops[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
