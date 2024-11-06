package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小F的糖果工厂能够生产n种不同种类的糖果，糖果的编号从1到n。
 * 每天，工厂可以生产编号为i的ci个糖果。
 * <p>
 * 今天，小F接到了一个特殊的订单，订单要求生产a包糖果，每包糖果都必须是同一种类的，
 * 并且每包糖果的数量不能少于b个。你能帮小F计算一下，工厂至少需要多少天才能完成这个订单吗？
 */
public class CandyFactory {

    public static int solution(int n, int a, int b, int[] candies) {
        //最多
        int min = Arrays.stream(candies).min().getAsInt();
        int l = 1;
        int r = a * b / (min * n) + 1; //最多天数
        while (l < r) {
            int mid = l + (r - l) / 2;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += candies[i] * mid / b;
            }
            if (sum < a) {
                l = mid + 1;
            } else {
                r = mid; //相等的情况下, 也需要-- 找到极限
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] candies1 = {7, 9, 6};
        int[] candies2 = {3, 10, 8, 4};
        int[] candies3 = {1, 10};

        System.out.println(solution(3, 10, 20, candies1) == 10);
        System.out.println(solution(4, 5, 15, candies2) == 4);
        System.out.println(solution(2, 100, 5, candies3) == 46);
    }
}
