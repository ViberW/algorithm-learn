package com.welph.leecode.part_1_500.part_361_380;

/**
 * 你的任务是计算 a^b (次方) 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * <p>
 * 示例 1：
 * 输入：a = 2, b = [3]
 * 输出：8
 * <p>
 * 示例 2：
 * 输入：a = 2, b = [1,0]
 * 输出：1024
 * <p>
 * 示例 3：
 * 输入：a = 1, b = [4,3,3,8,5,2]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：a = 2147483647, b = [2,0,0]
 * 输出：1198
 * ///////////////////////////////////
 * 1 <= a <= 2^31 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b 不含前导 0
 */
public class Solution372 {

    public static void main(String[] args) {
       /* System.out.println(superPow(1337, new int[]{3}));
        System.out.println(superPow(2, new int[]{1, 0}));
        System.out.println(superPow(1, new int[]{4, 3, 3, 8, 5, 2}));*/
        System.out.println(superPow(2147483647, new int[]{2, 0, 0}));
    }

    /**
     * a^(200) = ((a^20)^10) * (a^4) = ((((a^2)^10) * (a^0))^10) * (a^4)
     * ------------
     * ( a^b ) % 1337 = (a%1337)^b = 余数^b; ^_~
     */
    public static int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = 0; i < b.length; i++) {
            ans = (pow(ans, 10) * pow(a, b[i])) % 1337;
        }
        return ans;
    }

    /**
     * 为什么要自己实现,  否则会因为精度问题 导致计算结果错误
     */
    static int pow(int a, int b) {
        int ans = 1;
        a %= 1337;
        for (int i = 0; i < b; i++) {
            ans *= a;
            ans %= 1337;
        }
        return ans;
    }
}
