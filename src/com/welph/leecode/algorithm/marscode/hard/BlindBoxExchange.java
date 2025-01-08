package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U在玩一个盲盒交换游戏,给定两个参数:n和k。将以下公式代化简
 *
 * 1/2+1/2*(((2*k/(1+k))-1)^n)
 *
 * 这个公式最终的结果可以简化成一个最简分数p/q,小U想知道p+q的值模10的结果是多少。
 */
public class BlindBoxExchange {

    public static int solution(int n, int k) {
        //计算里面的数值:(2*k/(1+k))-1
        long molecule = 2 * k;
        long denominator = 1 + k;
        molecule -= denominator;
        if (molecule == 0) {
            return 3;
        }
        //找出两者的最小公倍数
        long mod = gcd(molecule, denominator);
        molecule /= mod;
        denominator /= mod;
        //然后就是n个 =>(d^n+m^n )/ 2*d^n  不能再简化了,至少d或m中不能同时存在2的质因子
        //但是d^n+m^n的值. 可能与分母2*d^n存在相同的质因子
        denominator = power(denominator, n);
        molecule = denominator + power(molecule, n);
        denominator = 2 * denominator;

        mod = gcd(molecule, denominator);
        molecule /= mod;
        denominator /= mod;
        return (int) ((denominator + molecule) % 10);
    }

    public static long power(long n, long m) {
        long result = 1;
        long base = n;
        //mod设置未100.是不是好一点???
        while (m > 0) {
            if ((m & 1) == 1) {
                result = (result * base);
            }
            base = (base * base);
            m >>= 1;
        }
        return result;
    }

    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public static void main(String[] args) {
//        System.out.println(solution(2, 5) == 1);
        System.out.println(solution(2, 6) == 6);
        System.out.println(solution(3, 7) == 9);
        System.out.println(solution(3, 9) == 9);
        System.out.println(solution(13, 17));
        System.out.println(solution(1, 10));
    }
}
