package mcw.test.offer;


/**
 * @author mcw 2020\1\19 0019-14:38
 *
 * 求最大连续子序列和
 */
public class Test30 {

    public int FindGreatestSumOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

    public static void main(String[] args) {
        System.out.println(new Test30().FindGreatestSumOfSubArray(new int[]{-3,-6,-2,7,-15,1,2,2}));
    }

}
