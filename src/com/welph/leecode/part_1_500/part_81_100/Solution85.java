package com.welph.leecode.part_1_500.part_81_100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个仅包含0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Solution85 {

    public static void main(String[] args) {
        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int xLen = matrix.length;
        if (xLen == 0) {
            return maxArea;
        }
        int yLen = matrix[0].length;
        if (yLen == 0) {
            return maxArea;
        }
        Stack<Integer>[] stacks = new Stack[yLen];
        int[][] widths = new int[yLen][xLen + 1];
        int i;
        for (i = 0; i < yLen; i++) {
            stacks[i] = new Stack<>();
        }
        int j;
        int width;
        Stack<Integer> s;
        for (i = 0; i <= xLen; i++) {
            width = 0;
            for (j = 0; j < yLen; j++) {
                int v = i == xLen ? 0 : matrix[i][j] - '0';
                if (v == 1) {
                    widths[j][i] = ++width;
                } else {
                    width = 0;
                }
                /*
                 * 当初的想法:
                 * 这一套逻辑就像是{@link Solution84}的单调栈
                 * 每个j的单独形成多个柱子, 相当于求同一j列下的最大矩形长度
                 */
                s = stacks[j];
                while (!s.isEmpty() && widths[j][i] < widths[j][s.peek()]) {
                    maxArea = Math.max(maxArea, widths[j][s.pop()] * (s.isEmpty() ? i : i - 1 - s.peek()));
                }
                s.push(i);
            }
        }
        return maxArea;
    }

    /**
     * 来自官方的单调栈的处理方案
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1; // 也是横向的多柱形图
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            // 从左和从右不断移动找出左右两边分别小于[i,j]处值的左值和右值
            // {@link Solution84#largestRectangleArea2()}
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
}
