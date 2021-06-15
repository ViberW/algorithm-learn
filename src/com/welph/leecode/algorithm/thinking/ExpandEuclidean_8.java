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
}
