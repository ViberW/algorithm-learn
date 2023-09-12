package com.welph.leecode.part_1_500.part_221_240;

/**
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 * <p>
 * 示例:
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 * 说明: 假设矩形面积不会超出 int 的范围。
 */
public class Solution223 {

    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    // 两个矩形合并的面积
    public static int computeArea(int A, int B, int C, int D,
            int E, int F, int G, int H) {
        // A -> C E-G
        int x = calculate(A, C, E, G);
        int y = calculate(B, D, F, H);
        return area(A, B, C, D) + area(E, F, G, H) - x * y;
    }

    private static int area(int a, int b, int c, int d) {
        return calculate(a, c) * calculate(b, d);
    }

    private static int calculate(int b, int d) {
        int tmp;
        if (b > d) {
            tmp = d;
            d = b;
            b = tmp;
        }
        return d - b;
    }

    // 找出两对点的 相交的长度
    private static int calculate(int b, int d,
            int f, int h) {
        int tmp;
        if (b > d) {
            tmp = d;
            d = b;
            b = tmp;
        }
        if (f > h) {
            tmp = h;
            h = f;
            f = tmp;
        }
        if (b < h && d > f) {
            return Math.min(d, h) - Math.max(b, f);
        }
        return 0;
    }

    /* 官方题解的精简 */

    public int computeArea2(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1); // 重叠的宽
        int overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);// 重叠的高
        int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
        return area1 + area2 - overlapArea;
    }

}
