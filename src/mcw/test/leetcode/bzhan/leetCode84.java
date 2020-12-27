package mcw.test.leetcode.bzhan;

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
