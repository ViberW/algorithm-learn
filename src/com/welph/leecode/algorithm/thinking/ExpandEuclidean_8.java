package com.welph.leecode.algorithm.thinking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [拓展欧几里得]
 * //欧几里得算法： 两个正整数a和b（a>b）,她们的最大公约数等于a除以b的余数c和b之间的最大公约数
 * ---------又名: [辗转相除法] {@link ExpandEuclidean_8#gcd(int, int)}
 * --------------------------------
 * <a>https://zhuanlan.zhihu.com/p/100567253</a>
 * @since 2021/6/11 11:16
 */
public class ExpandEuclidean_8 {

    public static void main(String[] args) {
        System.out.println(gcd(64, 72));
    }

    /**
     * 拓展欧几里得算法:
     * 例如求 Ax-By=1 的最小整数解
     */
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

    /**
     * 两数最大公约数 : 欧几里得算法
     */
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    /**
     * 九章算术
     */
    public static int gcd1(int a, int b) {
        if (b == a)
            return a;
        else if (a < b)
            return gcd1(b - a, a);
        else
            return gcd1(a - b, b);
    }

    /**
     * 欧几里得算法 针对两数相差较大时, 取模运算性能较低
     * 九章算术     相减的次数随两数差值变大而增多
     * --------------------
     * 两者结合: 由于位运算快
     * 当a和b均为偶数，gcb(a,b) = 2*gcb(a/2, b/2) = 2*gcb(a>>1, b>>1)
     * 当a为偶数，b为奇数，gcb(a,b) = gcb(a/2, b) = gcb(a>>1, b)
     * 当a为奇数，b为偶数，gcb(a,b) = gcb(a, b/2) = gcb(a, b>>1)
     * 当a和b均为奇数，利用九章算术运算一次，gcb(a,b) = gcb(b, a-b) 此时(a-b)一定为偶数
     */
    public static int gcd2(int a, int b) {
        if (b == a)
            return a;
        else if (a < b)
            return gcd2(b, a);
        else {
            if (((a & 1) != 1) && ((b & 1) != 1)) {//都是偶数
                return gcd2(a >> 1, b >> 1) << 1;
            } else if (((a & 1) != 1) && ((b & 1) == 1)) {//a偶数 b奇数
                return gcd2(a >> 1, b);
            } else if (((a & 1) == 1) && ((b & 1) != 1)) {//a奇数 b偶数
                return gcd2(a, b >> 1);
            } else {
                return gcd2(a, a - b);
            }
        }
    }
}
