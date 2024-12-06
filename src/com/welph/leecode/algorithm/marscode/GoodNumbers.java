package com.welph.leecode.algorithm.marscode;

/**
 * 小C定义一个正整数为“好数”，当且仅当这个数的相邻数位都不相同。
 *
 * 比如，1024 和 121 是好数，但 233 不是。
 *
 * 现在小C想知道，不大于给定整数n的好数有多少个。结果对 10^9 + 7 取模。
 */
public class GoodNumbers {

    public static int solution(int n) {
        int mod = 1000000007;
        //归纳总结: 不包含最高i位,  和包含i位需要剔除第二位不为i
        //f[i]为第i位不为0时,能够多少种好数
        // f[i] = f[i-1]*8 + f[i-2]*9  //表示i-1不等于i, 和i-2不为0
        // f[0]=1   f[1] = 9
        long result = 0;
        long[] fn = new long[12];
        int[] values = new int[12];
        fn[0] = 1;
        fn[1] = 9;
        int i = 0;
        while (n > 0) {
            if (i > 0) {
                result = (result + fn[i - 1] * 9) % mod;
            }
            if (i > 1) {
                fn[i] = (fn[i - 1] * 8 + fn[i - 2] * 9) % mod;
            }
            values[i] = n % 10;
            n /= 10;
            i++;
        }
        i--;
        for (int j = i; j >= 0; j--) {
            //1. 不包含i , [1,i-1]
            if (values[j] > 1) {
                int count = values[j] - 1;
                if (j != i && values[j + 1] < values[j] && values[j + 1] > 0) {
                    count--; //上一个数在1到i之间
                }
                result = (result + fn[j] * count) % mod;
            }
            if (i != j) {
                //2. 考虑当前为0
                if (values[j + 1] != 0 && values[j] > 0) {
                    result = (result + (j > 0 ? fn[j - 1] * 9 : 1)) % mod;
                }
                if (values[j + 1] == values[j]) {
                    break;
                }
                if (j == 0) { //主要时考虑到最后一个数还能使用时, 正好应对步骤1未取的value[j]的情况
                    result = (result + 1) % mod;
                }
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(15) == 14);
        System.out.println(solution(915) == 752);
        System.out.println(solution(5678) == 4202);
        System.out.println(solution(46899066) == 22859343);
        System.out.println(solution(49943688));
        System.out.println(solution(67093548));
    }
}
