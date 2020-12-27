package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\15 0015-13:12
 * Trapping Rain Water
 */
public class leetCode42 {
    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int max = 0;
        int leftmax = 0;
        int rightmax = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            leftmax = Math.max(leftmax, height[i]);
            rightmax = Math.max(rightmax, height[j]);
            if (leftmax < rightmax) {
                max += leftmax - height[i];
                i++;
            } else {
                max += rightmax - height[j];
                j--;
            }
        }
        return max;
    }

    /*public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }*/
}
