package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Stack;

/**
 * Largest Rectangle in Histogram(直方图中最大的矩形)
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author mcw 2020\6\29 0029-15:23
 */
public class leetCode84 {

    public int largestRectangleArea01(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();
        //从左往右遍历，依次找出每个柱子的最左边距
        for (int i = 0; i < n; i++) {
            //找出不小于当前柱子高度的柱子，记录它的下标
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            //记录当前柱子的最左边距
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        //从右往左遍历，依次找出每个柱子的最右边距
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }
        //计算每个柱子所能扩散的最大面积
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public int largestRectangleArea02(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }


    public static int largestRectangleArea(int[] heights) {
        //O(n)
        if (heights == null || heights.length == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();

        for (int curr = 0; curr < heights.length; curr++) {
            if (stack.isEmpty() || heights[curr] >= heights[stack.peek()]) {
                stack.push(curr);
            } else {
                int right = curr;
                int left = stack.pop();
                while (!stack.isEmpty() && heights[left] == heights[stack.peek()]) {
                    left = stack.pop();
                }
                int leftMost = (stack.isEmpty()) ? -1 : stack.peek();
                // right is number after right edge, left is number before left edge.so no need to minus one.
                max = Math.max(max, (right - leftMost - 1) * heights[left]);
                curr--;
            }
        }

        int rightMost = stack.peek() + 1;
        //here left is number before edge,right is also before edge
        while (!stack.isEmpty()) {
            int right = stack.pop();
            int left = (stack.isEmpty()) ? -1 : stack.peek();
            max = Math.max(max, (rightMost - left - 1) * heights[right]);
        }
        return max;
    }

    //O(n^2)
    public static int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int max = 0;
        for (int curr = 0; curr < heights.length; curr++) {
            if (curr == heights.length - 1 || heights[curr] > heights[curr + 1]) {
                int minHeight = heights[curr];
                for (int i = curr; i >= 0; i--) {
                    minHeight = Math.min(minHeight, heights[i]);
                    max = Math.max(max, minHeight * (curr - i));
                }
            }
        }
        return max;
    }
}
