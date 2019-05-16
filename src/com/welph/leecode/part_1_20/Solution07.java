package com.welph.leecode.part_1_20;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 负号不转边,  0在前去除掉 : -123 -> -321  120 -> 21`
 *
 * @author: Admin
 * @date: 2019/5/9
 * @Description: {相关描述}
 */
public class Solution07 {

    public static void main(String[] args) {
        int i = -123;
        System.out.println(reverse(i));
    }

    public static int reverse(int x) {
        boolean navigate = x < 0;
        StringBuffer stringBuffer = new StringBuffer().append(x);
        if (navigate) {
            stringBuffer.deleteCharAt(0);
        }
        stringBuffer = stringBuffer.reverse();
        long i = Long.valueOf(stringBuffer.toString()) * (navigate ? -1 : 1);
        if (i > Integer.MAX_VALUE || i < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) i;
    }

    /**
     * 正确解法: 每次取模. 取余做为当前的增量
     */
    public int reverse01(int x) {
        long res = 0;
        for (; x != 0; x /= 10)
            res = res * 10 + x % 10;
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }
}
