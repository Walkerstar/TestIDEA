package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * <p>
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * <p>
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 * @author mcw 2021\5\09 0009-16:20
 */
public class leetCode1482 {
    /**
     * @param bloomDay  第 i 朵花会在 bloomDay[i] 天 盛开
     * @param m 要制作 m 束花
     * @param k 每束花需要 k 朵
     * @return 摘 m 束花需要等待的最少的天数
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (m > bloomDay.length / k) {
            return -1;
        }
        int low = Integer.MAX_VALUE, high = 0;
        for (int value : bloomDay) {
            low = Math.min(low, value);
            high = Math.max(high, value);
        }
        while (low < high) {
            int days = (high - low) / 2 + low;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    //判断在给定的天数内能否制作出指定数量的花束
    private boolean canMake(int[] bloomDay, int days, int m, int k) {
        //制作的花束的数量
        int bouquets = 0;
        int flowers = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length && bouquets < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                //如果当前花的花期 大于 给定天数，则该花不能开放，
                //由于制作花束的花必须相邻，所以前面的花都不可用，将 flowers 清零
                flowers = 0;
            }
        }
        return bouquets >= m;
    }


}
