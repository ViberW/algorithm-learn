package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小C希望构造一个包含n个元素的数组，且满足以下条件：
 *
 * 数组中的所有元素两两不同。
 * 数组所有元素的最大公约数为 k。
 * 数组元素之和尽可能小。
 *
 * 任务是输出该数组元素之和的最小值。
 */
public class QualificationArraySum {

    //{@link PrimeSieve_17}
    public static int solution(int n, int k) {
        //从1开始, 找不超过k的数, 知道满足n个, 结果最终乘以3
        //由于1最大公约数为1, 所以只要有1就时结果
        //1,2,3....n 结果乘以K
        return k * n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        //1 2 3
        System.out.println(solution(3, 1) == 6);
        //2 4
        System.out.println(solution(2, 2) == 6);
        //1234 * 3
        System.out.println(solution(4, 3) == 30);
    }
}
