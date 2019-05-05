package com.welph.leecode;

import java.util.Stack;

/**
 * @author: Admin
 * @date: 2019/4/29
 * @Description: {相关描述} c
 */
public class Test {

    public static void main(String[] args) {
        //找出最大的一组
        int[] arr = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(0/10);
        System.out.println(largestRectangleArea(arr));
    }

    public static int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            //不断消耗栈中的数据,为空则跳出
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);//维持着单调增栈,保存着数据的下标
            } else {
                //栈顶元素小于当前元素
                //矩形面积为 右-左+1 * 高
                Integer pop = s.pop();
                int area = height[pop] * (s.isEmpty() ? i : i - 1 - s.peek());
                maxArea = Math.max(maxArea, area);
                //向左,不断寻找最大维持的宽
                i--;
            }
        }
        return maxArea;
    }


}
