package com.welph.leecode.algorithm.marscode.hard;

/**
 * 在一个神奇的二叉树中，结构非常独特：
 * <p>
 * 每层的节点值赋值方向是交替的，第一层从左到右，第二层从右到左，以此类推，且该二叉树有无穷多层。
 * 小R对这个二叉树充满了好奇，她想知道，在二叉树中两个节点之间x, y的路径长度是多少。
 * graph TD
 * 1((1));2((2));3((3));4((4));
 * 5((5));6((6));7((7));8((8));
 * 9((9));10((10));11((11));
 * 1---3;1---2;3---4;3---5;
 * 2---6;2---7;6---11;6---10;
 * 7---9;7---8;
 */
public class MagicTree {

    public static int solution(int x, int y) {
        // write code here
        //假设0代表左 1代表右
        //如11=> 1011  由于11反方向, 则应该异或取值 长度为4
        //如4=> 100 由于4为正方向, 则应该不变 长度为3
        int xi = 31 - Integer.numberOfLeadingZeros(x);
        if ((xi & 1) == 1) {
            x = (1 << xi) | (~x & ((1 << xi) - 1));
        }
        int yi = 31 - Integer.numberOfLeadingZeros(y);
        if ((yi & 1) == 1) {
            y = (1 << yi) | (~y & ((1 << yi) - 1));
        }
        while (x >= 0 && y >= 0 && xi >= 0 && yi >= 0) {
            if (((x >>> xi) & 1) != ((y >>> yi) & 1)) {
                return xi + 1 + yi + 1;
            }
            xi--;
            yi--;
        }
        //说明同一边
        return Math.max(xi, yi) + 1;
    }

    public static void main(String[] args) {
//        System.out.println(solution(11, 4) == 5);
//        System.out.println(solution(2, 5) == 3);
        System.out.println(solution(7, 7) == 0);
    }
}
