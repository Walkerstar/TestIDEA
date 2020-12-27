package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * @author mcw 2020\5\2 0002-15:03
 * 3 Sum Closest  找出 最接近目标数的 三个数的和？
 * 例：array S={-1,2,1,-4} target=1     最接近的和为 2 ，{ -1 + 2 + 1 = 2 }
 */
public class leetCode16 {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return target;
        }
        Arrays.sort(nums);
        int delta = nums[0] + nums[1] + nums[2] - target;
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int newdelta = nums[i] + nums[start] + nums[end] - target;
                if (newdelta == 0) {
                    return target;
                }
                if (Math.abs(delta) > Math.abs(newdelta)) {
                    delta = newdelta;
                }
                if (newdelta < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return target + delta;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }
}
