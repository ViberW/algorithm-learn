package com.welph.leecode.one;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 你不能倾斜容器，且 n 的值至少为 2
 *
 * @author: Admin
 * @date: 2019/5/14
 * @Description: {相关描述}
 */
public class Solution11 {

    public static void main(String[] args) {
        int[] list = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(list));
    }

    public static int maxArea(int[] height) {
        //双指针  从左右往中间靠拢
        int l = 0;
        int r = height.length - 1;
        int h;
        int maxArea = 0;
        while (l < r) {
            h = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, h * (r - l));
            //精髓在这
            //不断往中间趋近,因为宽一直减少,所以高度不变 面积就一直减少
            while (height[l] <= h && l < r) ++l;
            while (height[r] <= h && l < r) --r;
        }
        return maxArea;
    }
}
