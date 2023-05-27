package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 在样本中出现的次数。
 * <p>
 * 计算以下统计数据:
 * <p>
 * minimum ：样本中的最小元素。
 * maximum ：样品中的最大元素。
 * mean ：样本的平均值，计算为所有元素的总和除以元素总数。
 * median ：
 * 如果样本的元素个数是奇数，那么一旦样本排序后，中位数 median 就是中间的元素。
 * 如果样本中有偶数个元素，那么中位数median 就是样本排序后中间两个元素的平均值。
 * mode ：样本中出现次数最多的数字。保众数是 唯一 的。
 * 以浮点数数组的形式返回样本的统计信息 [minimum, maximum, mean, median, mode] 。与真实答案误差在 10-5 内的答案都可以通过。
 * <p>
 * 示例 1：
 * <p>
 * 输入：count =
 * [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
 * 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
 * 解释：用count表示的样本为[1,2,2,2,3,3,3,3]。
 * 最小值和最大值分别为1和3。
 * 均值是(1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375。
 * 因为样本的大小是偶数，所以中位数是中间两个元素2和3的平均值，也就是2.5。
 * 众数为3，因为它在样本中出现的次数最多。
 * <p>
 * 提示：
 * <p>
 * count.length == 256
 * 0 <= count[i] <= 10^9
 * 1 <= sum(count) <= 10^9
 * count 的众数是 唯一 的
 *
 * @author MCW 2023/5/27
 */
public class leetCode1093 {

    public double[] sampleStats(int[] count) {
        int n = count.length;
        //所有数字总共出现的次数
        int total = Arrays.stream(count).sum();
        //平均值
        double mean = 0.0;
        //中位数
        double median = 0.0;
        int minNum = 256;
        int maxNum = 0;
        //出现次数最多的数字
        int mode = 0;
        //中位数的位置
        int left = (total + 1) / 2;
        int right = (total + 2) / 2;

        int cnt = 0;
        int maxFreq = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (long) count[i] * i;
            if (count[i] > maxFreq) {
                maxFreq = count[i];
                mode = i;
            }
            if (count[i] > 0) {
                if (minNum == 256) {
                    minNum = i;
                }
                maxNum = i;
            }
            if (cnt < right && cnt + count[i] >= right) {
                median += i;
            }
            if (cnt < left && cnt + count[i] >= left) {
                median += i;
            }
            cnt += count[i];
        }
        mean = (double) sum / total;
        median = median / 2.0;
        return new double[]{minNum, maxNum, mean, median, mode};
    }
}
