package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U对数字倍数问题很感兴趣，她想知道在区间[l,r]内，有多少个数是a的倍数，或者是b的倍数，或者是c的倍数。你需要帮小U计算出这些数的个数。
 */
public class NumMultiple {
    public static int solution(int a, int b, int c, int l, int r) {
        // write code here
        int count = extracted(a, l, r);
        count += extracted(b, l, r);
        count += extracted(c, l, r);
        //计算两者相交的数据
        int lcm = (a * b) / gcd(a, b);  //最小公倍数=两数乘积除以最大公约数
        count -= extracted(lcm, l, r);
        count -= extracted((a * c) / gcd(a, c), l, r);
        count -= extracted((b * c) / gcd(b, c), l, r);
        //a,b,c共同的 因为在上面可能出现三者剔除共同拥有的.
        count += extracted((lcm * c) / gcd(lcm, c), l, r);
        return count;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static int extracted(int val, int l, int r) {
        return (r / val) - ((l - 1) / val);
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 3, 4, 1, 10) == 7);
        System.out.println(solution(5, 7, 11, 15, 100) == 34);
        System.out.println(solution(1, 1, 1, 1, 1000) == 1000);
    }
}
