package com.welph.leecode.part_1_500.part_81_100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 *
 * @see com.welph.leecode.part_1_500.part_81_100.images/histogram1.png
 *      <p>
 *      以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为[2,1,5,6,2,3]。
 *      <p>
 * @see com.welph.leecode.part_1_500.part_81_100.images/histogram2.png
 *      <p>
 *      图中阴影部分为所能勾勒出的最大矩形面积，其面积为10个单位。
 */
public class Solution84 {

    public static void main(String[] args) {
        int[] heights = { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(heights));
    }

    /*
     * {@link Test} 单调栈
     */
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                Integer pop = s.pop();
                int area = heights[pop] * (s.isEmpty() ? i : i - 1 - s.peek());
                maxArea = Math.max(maxArea, area);
                i--;
            }
        }
        return maxArea;
    }

    /**
     * 相较于上面的方法 这个只能算是另一个解, 空间利用没有上面方法好
     * 
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {// 标记i左边的小于它的位置
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {// 标记i右边的小于它的位置
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            // 知道左右两边小于i的位置, 那么中间就是大于或者等于i的柱子, 那么高h[i]的宽度等于左右两边
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

}
