package mcw.test.leetcode.bzhan;

/**
 * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，
 * 其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * <p>
 * 你按照如下规则进行一场游戏：
 * <p>
 * 你从第 0 天开始吃糖果。<br>
 * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。<br>
 * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。<br>
 * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，
 * 你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * <p>
 * 请你返回得到的数组 answer 。
 *
 * @author mcw 2021/6/1 10:35
 */
public class leetCode1744 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;

        //前缀和 记录从第 0 个类型，到第 i 个类型一共能吃到多少糖果
        long[] sum = new long[n];
        sum[0] = candiesCount[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }

        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; i++) {
            int[] query = queries[i];
            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];
            //因为从第 0 天开始吃糖果，，所以对于第 i 个询问，我们可以吃（ favoriteDay_i+1 ）天的糖果
            //而每天最少吃一颗糖果，则最少糖果数量为 favoriteDay + 1
            long x1 = favoriteDay + 1;

            //每天最多吃 dailyCap 颗糖果，则最多可吃糖果数量为 （favoriteDay + 1）* dailyCap
            long y1 = (long) (favoriteDay + 1) * dailyCap;

            //如果当前类型的糖果能被吃到，最少是一颗，所以第 favoriteType 个类型吃到糖果的最少的数量为 前缀和数组中前一个类型糖果的数量加一
            long x2 = favoriteType == 0 ? 1 : sum[favoriteType - 1] + 1;

            //最大是能全部吃到，所以第 favoriteType 个类型吃到糖果的最多的数量为 前缀和数组中当前类型糖果的数量
            long y2 = sum[favoriteType];

            //如果[x1,y1] , [x2,y2] 两个区间不相交，则说明 吃不到第 favoriteType_i 类型的糖果
            ans[i] = !(x1 > y2 || y1 < x2);
        }
        return ans;
    }
}
