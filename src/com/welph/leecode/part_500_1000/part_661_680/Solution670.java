package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 *
 * 给定数字的范围是 [0, 108]
 */
public class Solution670 {

    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
        System.out.println(maximumSwap(9973));
        System.out.println(maximumSwap(1993));
    }


    public static int maximumSwap(int num) {
        int originNum = num;
        //交换两个数, 则从右向左遍历, 获取到最大值
        int l = 1, max = 0, r = 1, delta = 0;
        while (num > 0) {
            int cur = num % 10;
            if (cur < max) {
                int t = originNum / r % 10 - originNum / l % 10;
                delta = t * l - t * r;
            } else if (cur > max){
                r = l;
                max = cur;
            }
            num /= 10;
            l *= 10;
        }
        return originNum + delta;
    }
}
