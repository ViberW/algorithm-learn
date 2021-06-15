package com.welph.leecode.algorithm.thinking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [逆元]
 * ---------------------
 * 在数论中，如果 ab =1 (mod P) ，我们就说 a 和 b 在模 p 意义下互为乘法逆元，记作 a =inv(b) 。
 * inv(a) 其实可以看做模 p 意义下的 1/a;
 * ---------------------
 * @since 2021/6/11 14:22
 */
public class InverseElement_9 {

    /**
     * 应用场景之一: 加减法和乘法对取模运算都是封闭的，所以可以处处取模来避免溢出。
     */
    public static void main(String[] args) {
        //例如, 求两个值同2取模的结果, 此时单纯的相加可能造成数值溢出
        //int x = (Integer.MAX_VALUE + Integer.MAX_VALUE) % 2;
    }

    /**
     * {@link ExpandEuclidean_8} 拓展欧几里得算法方式
     */
    public int inv1(int a, int p) {
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        if (exgcd(a, p, x, y) == -1) {//无解
            return -1;
        }
        return (x.get() % p + p) % p;
    }

    public int exgcd(int a, int b, AtomicInteger x, AtomicInteger y) {
        if (b == 0) {
            x.set(1);
            y.set(0);
            return a;
        }
        int d = exgcd(b, a % b, y, x); //这里交换了x和y
        y.addAndGet(-(a / b) * x.get());
        return d;
    }

    /////////////////////////////////////////////////////////////////
    public int inv2(int a, int p) {
        return qpow(a, p - 2, p);
    }

    /**
     * [费马小定理]
     * 若 p 是质数，且 gcd(a, p)=1 ，则有 a^(p-1) =1 (mod p)
     * 从逆元的定义推导，可得 a.inv(a)=1=a^(p-1) (mod p) ，于是有 inv(a) =a^(p-3) (mod p)
     */
    private int qpow(int a, int n, int p) {//快速幂
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1)
                ans = ans % p * a % p;
            a = a % p * a % p;
            n >>= 1;
        }
        return ans;
    }
    /////////////////////////////////////////////////////////////////

    /**
     * [线性递推]
     * //给定 n,p 求 1~n 中所有整数在模 p 意义下的乘法逆元。
     */
    public int inv3(int a, int p) {
        if (Inv[a] > 0)
            return Inv[a];
        Inv[a] = mod(-p / a * inv3(p % a, p), p);
        return Inv[a];
    }

    int[] Inv = {0, 1};

    public int mod(int a, int p) {
        return (a % p + p) % p;
    }

}
