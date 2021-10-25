package com.welph.leecode.part_1_500.part_81_100;

import java.util.Stack;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
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
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
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
     * todo 横向操作 可能容易操作
     */
}
