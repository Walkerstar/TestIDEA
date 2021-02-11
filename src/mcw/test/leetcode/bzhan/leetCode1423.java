package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 *
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 *
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 *
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * @author mcw 2021/2/06 15:51
 */
public class leetCode1423 {

    /**
     * 由于剩余卡牌是连续的，使用一个固定长度为 n−k 的滑动窗口对数组 cardPoints 进行遍历，求出滑动窗口最小值，
     * 然后用所有卡牌的点数之和减去该最小值，即得到了拿走卡牌点数之和的最大值。
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        //滑动窗口大小为 n-k
        int windowSize = n - k;
        //选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; i++) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }
}
