package com.welph.leecode.part_500_1000.part_501_520;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * 输入：num = 28
 * 输出：true
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * <p>
 * 示例 2：
 * 输入：num = 7
 * 输出：false
 * <p>
 * 提示：
 * 1 <= num <= 10^8
 */
public class Solution507 {

    public static void main(String[] args) {
        System.out.println(checkPerfectNumber(28));
        System.out.println(checkPerfectNumber(7));
        System.out.println(checkPerfectNumber(1));
    }

    /**
     * 有种{欧拉函数}的概念, 在[1,sqrt(num)] 做处理.
     */
    public static boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int m = (int) Math.sqrt(num);
        int total = 1;
        for (int i = 2; i <= m; i++) {
            if (num % i == 0) {
                if (i == m && num == i * i) {
                    total += i;
                } else {
                    total += i + num / i;
                }
            }
        }
        return total == num;
    }
}
