package com.welph.leecode.part_1_500.part_61_80;

/**
 * 实现int sqrt(int x)函数。
 * <p>
 * 计算并返回x的平方根，其中x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *     由于返回类型是整数，小数部分将被舍去。
 */
public class Solution69 {

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }

    /**
     * 二分法  主要是需要注意int临界值 2^31-1 ->2^16以及乘积大于0
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        int min = 0;
        int max = x;
        int limit = 1 << 16;
        int mid;
        int result;
        while (min <= max) {
            mid = (min + max) / 2;
            if (mid >= limit) {
                max = mid - 1;
                continue;
            }
            result = mid * mid;
            //判断是否超过阈值
            if (result < 0 || result > x) {
                max = mid - 1;
            } else if (result < x) {
                min = mid + 1;
            } else {
                return mid;
            }
        }
        return max;
    }

    /**
     * 牛顿迭代法：
     * 由函数f(x)=x^2-a，我们求导可以知道，函数上任意一点(x,y)的切线的[斜率为2x]。
     * ------------------------------ 注意 这里的斜率是 2x 那么代表直线: m=(2x)*l + k
     * <p>
     * 假设过点（x0,y0）的切线方程为y=kx+b，那么切线与x轴的交点横坐标为-b/k。
     * <p>
     * 而b=y0-kx0,k=2x0,y0=x0^2-a,化简-b/k=（x0+a/x0）/2。
     * <p>
     * 也就是说（x0+a/x0）/2是过点（x0,y0）的切线与x轴的交点的横坐标。
     * <p>
     * 记（x0+a/x0）/2=x',继续求过点（x',f(x')）的切线与x轴的交点的横坐标x''，很明显x''比x'更靠近函数f(x)=x^2-a与x轴的交点的横坐标(即a的正平方根)。
     *
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        //一开始以自己作为本身
        long a = x;
        while (a * a > x) {
            a = (a + x / a) / 2;
        }
        return (int) a;
    }

}
