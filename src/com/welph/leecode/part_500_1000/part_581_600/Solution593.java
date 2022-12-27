package com.welph.leecode.part_500_1000.part_581_600;

/**
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * 点的坐标 pi 表示为 [xi, yi] 。 输入没有任何顺序 。
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * <p>
 * 示例 1:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * <p>
 * 示例 2:
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * <p>
 * 示例 3:
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 * <p>
 * 提示:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4 <= xi, yi <= 10^4
 */
public class Solution593 {

    public static void main(String[] args) {
//        System.out.println(validSquare(
//                new int[]{0, 0},
//                new int[]{1, 1},
//                new int[]{1, 0},
//                new int[]{0, 1}
//        ));
        System.out.println(validSquare(
                new int[]{0, 1},
                new int[]{1, 1},
                new int[]{1, 1},
                new int[]{1, 0}
        ));
    }

    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int line1 = calculateLine(p1, p2);
        int line2 = calculateLine(p1, p3);
        int line3 = calculateLine(p1, p4);
        //可能没有对角线
        int max = Math.max(line1, Math.max(line2, line3));
        if (max == line1) {
            return calculateSquare(line1, line2, line3, p2, p3, p4);
        } else if (max == line2) {
            return calculateSquare(line2, line1, line3, p3, p2, p4);
        } else {
            return calculateSquare(line3, line1, line2, p4, p2, p3);
        }
    }

    private static boolean calculateSquare(int line1, int line2, int line3,
                                           int[] p1, int[] p2, int[] p3) {
        if (line2 != line3 || line2 == 0 || line2 + line3 != line1) {
            return false;
        }
        int l1 = calculateLine(p1, p2);
        int l2 = calculateLine(p1, p3);
        int l3 = calculateLine(p2, p3);
        return l1 == l2 && l1 == line2 && l3 == line1;
    }

    private static int calculateLine(int[] p1, int[] p2) {
        int y = p2[1] - p1[1];
        int x = p2[0] - p1[0];
        return y * y + x * x;
    }

}
