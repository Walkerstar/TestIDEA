package mcw.test.leetcode.niuke;

import java.util.Arrays;

/**
 * @author mcw 2020\2\23 0023-21:53
 *
 * 有 N 个小朋友站在一排，每个小朋友都有一个评分，给他们分糖
 * 1.每个小朋友至少一个  2.分数高的小朋友比他旁边得分低的小朋友分的糖多
 */
public class Test16 {

    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;

        int[] count = new int[ratings.length];
        //初始  每个孩子都有一个糖果
        Arrays.fill(count, 1);
        int sum = 0;

        //从左到右
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1])
                count[i] = count[i - 1] + 1;
        }

        //从右到左
        for (int i = ratings.length - 1; i > 0; i--) {
            sum += count[i];
            if (ratings[i] < ratings[i - 1] && count[i] >= count[i - 1])
                count[i - 1] = count[i] + 1;
        }

        sum += count[0];
        return sum;
    }
}
