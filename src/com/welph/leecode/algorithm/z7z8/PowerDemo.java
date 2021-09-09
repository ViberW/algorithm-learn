package com.welph.leecode.algorithm.z7z8;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 手动实现Math.power(x, y)方法
 * @since 2021/9/9 15:10
 */
public class PowerDemo {

    public static void main(String[] args) {
        System.out.println(power(3, 2));
        System.out.println(power1(3, 2));
        //3的2次幂值为9对11取模后的结果
        System.out.println(power2(3, 2, 11));
    }

    /**
     * 递归法 O(logN)
     */
    public static int power(int x, int y) {
        if (y == 0) {
            return 1;
        }
        int power = power(x, (y - y % 2) / 2);
        return power * power * (y % 2 == 1 ? x : 1);
    }

    /**
     * 平方幂(快速幂)
     * --------
     * 将y二进制化
     * 如y=7 ==>(0111)
     * x^y ==> x^(0111) ==> x^(2^2+2^1+2^0)
     * ==> (x^(2^2))*(x^(2^1))*(x^(2^0))
     */
    public static int power1(int x, int y) {
        int power = 1;
        int t = x;
        while (y != 0) {
            if ((y & 1) != 0) {
                power *= t;
            }
            t = t * t;
            y = y >> 1;
        }
        return power;
    }

    /**
     * 快速幂的取模方式
     *
     * @param n 取模值
     */
    public static int power2(int x, int y, int n) {
        if (n == 0) {
            return 1 % n;
        }
        long ans = 1;
        long base = x % n;
        while (y != 0) {
            if ((y & 1) == 1) {
                ans = (ans * base) % n;
            }
            //为了避免超出long的范围，所以取三次模
            base = (base % n) * (base % n) % n;
            y >>= 1;
        }
        return (int) ans;
    }
}
