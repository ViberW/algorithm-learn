package com.welph.leecode.algorithm.marscode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 小U得到一个数列，数列的定义如下：
 *
 * 初始条件为 f(1) = a, f(2) = b。
 * 对于第 i 项，数列满足递推关系：f(i) = f(i-1) * f(i-2) * c^d。
 * 你需要计算出数列的第 n 项的因子数量。由于答案可能很大，请对 10^9 + 7 取模。
 */
public class SequenceFactors {

    public static int solution(int a, int b, int c, int d, int n) {
        //相当于是将a, b, c 因式分解. 等到不同个数的因子
        int mod = 1000000007;
        //对a进行因式分解
        Map<Integer, Integer> factor; //质因子,不包含1
        if (n == 1) {
            factor = metaFactor(a);
        } else if (n == 2) {
            factor = metaFactor(b);
        } else {
            factor = new HashMap<>();
            Map<Integer, Integer> factorA = metaFactor(a);
            Map<Integer, Integer> factorB = metaFactor(b);
            Map<Integer, Integer> factorC = metaFactor(c);
            int countA = 0;
            int countB = 1;
            int countC = 0; //c的d次方
            //前一个值
            int _countA = 1;
            int _countB = 0;
            int _countC = 0;
            int tmp;
            for (int i = 3; i <= n; i++) {
                tmp = _countA;
                _countA = countA;
                countA = countA + tmp;

                tmp = _countB;
                _countB = countB;
                countB = countB + tmp;

                tmp = _countC;
                _countC = countC;
                countC = countC + tmp + 1;
            }
            int t_countA = countA;
            factorA.forEach((k, v) -> factor.put(k, v * t_countA + factor.getOrDefault(k, 0)));
            int t_countB = countB;
            factorB.forEach((k, v) -> factor.put(k, v * t_countB + factor.getOrDefault(k, 0)));
            int t_countC = d * countC; //这里的c^d 是代表c的d次方. 不是异或
            factorC.forEach((k, v) -> factor.put(k, v * t_countC + factor.getOrDefault(k, 0)));
        }
        // 有m个x0 有m1个x1.....有mi个xi , 在这些个质因子中随意选择. 但每组不能重复 x0>1
        long result = 1; //因子为1
        if (!factor.isEmpty()) {
            //这里每个选择的可能是每种值可能数+1(什么都不选择到所有都选择)相乘 并减去1 (代表每个因子都没有选择)
            long multi = 1;
            for (Integer value : factor.values()) {
                multi = (multi * (value + 1)) % mod;
            }
            multi--;//减去1 (代表每个因子都没有选择)
            result = (result + multi) % mod;
        }
        return (int) result;
    }

    private static Map<Integer, Integer> metaFactor(int v) {
        Map<Integer, Integer> factor = new HashMap<>();
        int i = 2;
        while (i * i <= v) {
            int count = 0;
            while (v % i == 0) {
                count++;
                v /= i;
            }
            factor.put(i, count);
            i++;
        }
        if (v > 1) {
            factor.put(v, 1);
        }
        return factor;
    }

    public static void main(String[] args) {
        System.out.println(solution(1, 2, 3, 4, 3) == 10);
        System.out.println(solution(2, 3, 5, 2, 4) == 30);
        System.out.println(solution(1, 1, 2, 1, 5) == 5);
        System.out.println(solution(2, 15, 10, 6, 17));
    }
}
