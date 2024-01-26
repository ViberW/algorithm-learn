package com.welph.leecode.part_1_500.part_241_260;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 示例:
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
public class Solution258 {

    public static void main(String[] args) {
        System.out.println(addDigits(38));
    }

    //不使用循环递归, 时间O(1)  -- 最终结果一定是个小于10的值
    //todo 这个数学题要好好理解  10进制转9进制.
    //<a>https://leetcode-cn.com/problems/add-digits/solution/java-o1jie-fa-de-ge-ren-li-jie-by-liveforexperienc/</a>
    public static int addDigits(int num) {
        //因为num>0时 结果一定是大于0 所以不能直接num%9 这样会有0值
        //又有num=0时 结果为0 
        // 所以需要num-1 (mod 9) 
        return (num - 1) % 9 + 1;
    }

    //递归的方法
    public static int addDigits1(int num) {
        while (num >= 10) {
            int v = 0;
            while (num > 0) {
                v += num % 10;
                num /= 10;
            }
            num = v;
        }
        return num;
    }
}
