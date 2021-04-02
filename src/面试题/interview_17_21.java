package 面试题;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 *
 * @author mcw 2021\4\2 0002-15:26
 */
public class interview_17_21 {

    /**
     * 方法一：动态规划
     * 使用动态规划的方法，可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。
     * <p>
     * 创建两个长度为 n 的数组 leftMax 和 rightMax。对于 0≤i<n，leftMax[i] 表示下标 i 及其左边的位置中， height 的最大高度，
     * rightMax[i] 表示下标   i 及其右边的位置中， height 的最大高度。
     * <p>
     * 显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。两个数组的其余元素的计算如下：
     * <p>
     * 当 1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])；
     * <p>
     * 当 0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])。
     * <p>
     * 因此可以正向遍历数组  height 得到数组 leftMax 的每个元素值，反向遍历数组  height 得到数组 rightMax 的每个元素值。
     * <p>
     * 在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0≤i<n，下标  i 处能接的水的量等于 min(leftMax[i],rightMax[i])−height[i]。
     * 遍历每个下标位置即可得到能接的水的总量。
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 方法二：单调栈
     * 除了计算并存储每个位置两边的最大高度以外，也可以用单调栈计算能接的水的总量。
     * <p>
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组   height 中的元素递减。
     * <p>
     * 从左到右遍历数组，遍历到下标   i 时，如果栈内至少有两个元素，记栈顶元素为   top，  top 的下面一个元素是   left，
     * 则一定有  height[left]≥height[top]。如果  height[i]>height[top]，则得到一个可以接雨水的区域，该区域的宽度是 i−left−1，
     * 高度是 min(height[left],height[i])−height[top]，根据宽度和高度即可计算得到该区域能接的水的量。
     * <p>
     * 为了得到   left，需要将   top 出栈。在对   top 计算能接的水的量之后，  left 变成新的   top，重复上述操作，直到栈变为空，
     * 或者栈顶下标对应的 height 中的元素大于或等于 height[i]。
     * <p>
     * 在对下标 i 处计算能接的水的量之后，将  i 入栈，继续遍历后面的下标，计算能接的水的量。遍历结束之后即可得到能接的水的总量。
     */
    public int trap1(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i] - height[top]);
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 方法三：双指针
     * <p>
     * 维护两个指针 left 和 right，以及两个变量 leftMax 和 rightMax，初始时 left=0,right=n−1,leftMax=0,rightMax=0。
     * 指针 left 只会向右移动，指针 right 只会向左移动，在移动指针的过程中维护两个变量 leftMax 和  rightMax 的值。
     * <p>
     * 当两个指针没有相遇时，进行如下操作：
     * <p>
     * .使用 height[left] 和 height[right] 的值更新 leftMax 和  rightMax 的值；
     * <p>
     * .如果 height[left]<height[right]，则必有 leftMax<rightMax，下标 left 处能接的水的量等于 leftMax−height[left]，
     * 将下标 left 处能接的水的量加到能接的水的总量，然后将 left 加 1（即向右移动一位）；
     * <p>
     * .如果 height[left]≥height[right]，则必有 leftMax≥rightMax，下标 right 处能接的水的量等于 rightMax−height[right]，
     * 将下标 right 处能接的水的量加到能接的水的总量，然后将 right 减 1（即向左移动一位）。
     * <p>
     * 当两个指针相遇时，即可得到能接的水的总量。
     */
    public int trap2(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }
}
