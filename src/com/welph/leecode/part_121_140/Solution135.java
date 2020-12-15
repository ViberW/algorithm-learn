package com.welph.leecode.part_121_140;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class Solution135 {
    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        System.out.println(candy(ratings));
    }

    /**
     * 贪心算法 --  初始所有有1个糖果,从左到右判断, 再由右向左遍历
     * //todo 很经典
     */
    public static int candy(int[] ratings) {
        int len = ratings.length;
        int total = len; //每人一个糖果
        //额外的糖果
        int[] tmp = new int[len];
        for (int i = 1; i < len; i++) {
            //从左到右遍历, 若是i+1 > i 则i+1 一定是多一个额外糖果
            if (ratings[i] > ratings[i - 1]) {
                tmp[i] = tmp[i - 1] + 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                tmp[i] = Math.max(tmp[i], tmp[i + 1] + 1); //保证了i 起码比 [i+1]+1 大
            }
        }
        total += Arrays.stream(tmp).sum();
        return total;
    }
}
