package mcw.test.leetcode.bzhan;

import java.util.Stack;

/**
 * Maximal Rectangle
 * 给定一个由 0 ， 1，填充的二维数组，找到仅包含 1 的目标矩形，返回这个区域
 * @author mcw 2020\6\29 0029-14:58
 */
public class leetCode85 {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            int area = largestRectangleArea(heights);
            max = Math.max(max, area);
        }
        return max;
    }

    private static int largestRectangleArea(int[] heights) {
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

}
