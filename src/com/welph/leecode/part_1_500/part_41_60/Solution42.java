package com.welph.leecode.part_1_500.part_41_60;

import java.util.Stack;

/**
 * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）
 * 示例：输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Solution42 {

    /**
     * 当前题目在思考时一直纠结栈存放每个节点值而纠结， 想着一次性计算一个大块的面积，而没想到每个小的块的面积叠加
     */
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }


    public static int trap(int[] height) {
        // 每个水平模块的
        if (null == height || height.length < 3) {
            return 0;
        }
        int ans = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        while (current < height.length) {
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    /**
     * 动态编程：
     * 认为左一直升  后去左边的最大面积
     * 认为右一直降  后去右边的最大面积
     *
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        if (null == height || height.length < 3) {
            return 0;
        }
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        //计算左边
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        //计算右边
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        //遍历，对于值一定是在左右两间最小值减去本身
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 双指针法：good!
     * 根据动态编程来的
     * //因为左 是递增
     * //因为右 是递减
     *
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                //计算当前的left，因为一定是使用left计算
                if (height[left] > left_max) {
                    //说明更新,不需要添加面积
                    left_max = height[left];
                } else {
                    //使用max
                    ans += left_max - height[left];
                }
                left++;
            } else {
                //计算当前的right，因为一定是使用right计算
                if (height[right] > right_max) {
                    //说明更新,不需要添加面积
                    right_max = height[right];
                } else {
                    //使用max
                    ans += right_max - height[right];
                }
                right++;
            }
        }
        return ans;
    }
}
